package com.example.longecological.config.rocketmq;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.async.AsyncBenefitAlgebraService;
import com.example.longecological.service.async.AsyncUserPerformanceService;
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
@RocketMQMessageListener(consumerGroup = "group-" + RocketMqConstants.order_reward_topic,topic = RocketMqConstants.order_reward_topic,consumeMode=ConsumeMode.ORDERLY)
public class OrderRewardConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private AsyncBenefitMapper asyncBenefitMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AsyncBenefitAlgebraService asyncBenefitAlgebraService;
    @Autowired
    private AsyncUserPerformanceService asyncUserPerformanceService;
    
    
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
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			Map<String, Object> data = (Map<String, Object>) JSON.parse(arg0);
			//更新订单状态
			this.updateOrderStatus(StringUtil.getMapValue(data, "order_id"), "00", "02", date, time);
			//查询用户信息
			Map<String, Object> user = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(data, "user_id"));
			//处理层级收益
			asyncBenefitAlgebraService.dealUserOrderAlgebraBenefit(data, user, date, time);
			//处理个人业绩
			asyncUserPerformanceService.dealPersonPerformance(StringUtil.getMapValue(data, "user_id"), new BigDecimal(StringUtil.getMapValue(data, "cash_num")), date, time);
			//处理团队业绩
			if(!StringUtil.isEmpty(StringUtil.getMapValue(user, "parent_chain"))) {
				asyncUserPerformanceService.dealTeamPerformance(StringUtil.getMapValue(user, "parent_chain"), new BigDecimal(StringUtil.getMapValue(data, "cash_num")), date, time);
			}
			//更新订单状态
			this.updateOrderStatus(StringUtil.getMapValue(data, "order_id"), "02", "09", date, time);
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void updateOrderStatus(String id, String old_status, String new_status, String date, String time) throws Exception{
		Map<String, Object> order = new HashMap<>();
		order.put("id", id);
		order.put("old_status", old_status);
		order.put("new_status", new_status);
		order.put("up_date", date);
		order.put("up_time", time);
		int num = asyncBenefitMapper.updateOrderStatus(order);
		if(num != 1) throw new Exception("订单状态更新异常");
	}

	@Override
	public void prepareStart(DefaultMQPushConsumer consumer) {
		 consumer.setMaxReconsumeTimes(2);
	}
	
}
