package com.example.longecological.config.rocketmq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.constant.RocketMqConstants;
import com.example.longecological.utils.MqLevelUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
  * @ClassName: AuctionRefundDepositConsumer
  * @Description: 延迟队列
  * @date 2023年4月17日 下午2:55:35
  *
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "group-" + RocketMqConstants.delay_topic,topic = RocketMqConstants.delay_topic)
public class DelayConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Override
	public void onMessage(String arg0) {
		log.info(" topic:{}, arg0:{} ",RocketMqConstants.delay_topic,arg0);
		JSONObject jSONObject = JSONObject.parseObject(arg0);
		String targetTopic = jSONObject.getString("targetTopic");
		int delaySecond = jSONObject.getIntValue("delaySecond");
		Map<String,Object> map = getDelayLevel(delaySecond,targetTopic);
		log.info(" map:{} ", JSONObject.toJSONString(map));
		jSONObject.put("targetTopic", map.get("targetTopic"));
		jSONObject.put("delaySecond", map.get("delaySecond"));
		int level = Integer.parseInt(map.get("level").toString());
		rocketMQTemplate.syncSend(map.get("sendTopic").toString(),
				MessageBuilder.withPayload(jSONObject.toJSONString()).build(),3000,level);
	}
	
	//18个等级 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
	public Map<String,Object> getDelayLevel(int delaySecond,String topic){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<Integer,Integer> levelMap = MqLevelUtil.getInstance().getLevelMap();
		if(levelMap.containsKey(delaySecond)){
			map.put("targetTopic", topic);
			map.put("delaySecond", 0);
			map.put("level", levelMap.get(delaySecond));
			map.put("sendTopic", topic);
		}else{
			int second = MqLevelUtil.getInstance().getNearLevel(delaySecond);
			int surplus = delaySecond - second;//剩余秒数
			if(surplus <= 0){
				map.put("targetTopic", topic);
				map.put("delaySecond", 0);
				map.put("level", 1);
				map.put("sendTopic", topic);
			}else{
				map.put("targetTopic", topic);
				map.put("delaySecond", surplus);
				map.put("level", levelMap.get(second));
				map.put("sendTopic", RocketMqConstants.delay_topic);
			}
		}
		return map;
	}

	@Override
	public void prepareStart(DefaultMQPushConsumer consumer) {
		consumer.setMaxReconsumeTimes(2);
	}
	
	public static void main(String[] args) {
		int a = 20;
		ExecutorService executorService = Executors.newFixedThreadPool(a);
		Object obj = new Object();
		List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < a; i++) {
//            executorService.execute(() -> {
        	Thread thread = new Thread(()->{
                	String topic = "1";
            		int delaySecond = (int) ((Math.random() * 9 + 1) * Math.pow(10, 5 - 1));
            		Map<String,Object> map = new HashMap<String, Object>();
            		Map<Integer,Integer> levelMap = MqLevelUtil.getInstance().getLevelMap();
            		if(levelMap.containsKey(delaySecond)){
            			map.put("targetTopic", topic);
            			map.put("delaySecond", 0);
            			map.put("level", levelMap.get(delaySecond));
            			map.put("sendTopic", topic);
            		}else{
            			int second = MqLevelUtil.getInstance().getNearLevel(delaySecond);
            			int surplus = delaySecond - second;//剩余秒数
            			if(surplus <= 0){
            				map.put("targetTopic", topic);
            				map.put("delaySecond", 0);
            				map.put("level", 1);
            				map.put("sendTopic", topic);
            			}else{
            				map.put("targetTopic", topic);
            				map.put("delaySecond", surplus);
            				map.put("level", levelMap.get(second));
            				map.put("sendTopic", RocketMqConstants.delay_topic);
            			}
            		}
            		System.out.println(Thread.currentThread().getName()+map);
        		});
            	
//            });
        	list.add(thread);
        }
        
	}
}
