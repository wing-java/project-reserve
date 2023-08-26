package com.example.longecological.config.rocketmq;

import com.alibaba.fastjson.JSON;
import com.example.longecological.constant.RocketMqConstants;
import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.service.async.AsyncBenefitDayService;
import com.example.longecological.utils.SpringUtils;
import com.example.longecological.utils.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Map;

/**
 * 
  * @ClassName: OrderRewardConsumer
  *
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "group-" + RocketMqConstants.test_topic,topic = RocketMqConstants.test_topic,consumeMode=ConsumeMode.ORDERLY)
public class TestConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    
    
	@Override
	public void onMessage(String arg0) {
		log.info(" topic:{}, arg0:{} ",RocketMqConstants.test_topic,arg0);
		SpringUtils.getAopProxy(this).reward(arg0);
		
	}
	
	/**
	 * 
	* @Title: match 
	* @param: 
	* @Description: 奖励
	* @return void
	 */
	@Transactional
	public void reward(String arg0) {
		try {
			System.out.println(arg0);
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public void prepareStart(DefaultMQPushConsumer consumer) {
		 consumer.setMaxReconsumeTimes(2);
	}
	
}
