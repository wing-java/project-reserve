package com.example.longecological.config.rocketmq;

import java.util.Map;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.example.longecological.constant.RocketMqConstants;
import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.service.async.AsyncBenefitYearService;
import com.example.longecological.utils.SpringUtils;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
  * @ClassName: OrderRewardConsumer
  *
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "group-" + RocketMqConstants.order_benefit_year_topic,topic = RocketMqConstants.order_benefit_year_topic,consumeMode=ConsumeMode.ORDERLY)
public class OrderBenefitYearConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private AsyncBenefitMapper asyncBenefitMapper;
    @Autowired
    AsyncBenefitYearService asyncBenefitYearService;
    
    
	@Override
	public void onMessage(String arg0) {
		log.info(" topic:{}, arg0:{} ",RocketMqConstants.order_reward_topic,arg0);
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
//			String date = TimeUtil.getDayString();
//			String time = TimeUtil.getTimeString();
			Map<String, Object> data = (Map<String, Object>) JSON.parse(arg0);
			Map<String, Object> order = asyncBenefitMapper.getUserOrderById(StringUtil.getMapValue(data, "id"));
			asyncBenefitYearService.dealUserOrderYearBenefit(order, StringUtil.getMapValue(data, "date"), StringUtil.getMapValue(data, "time"));
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
