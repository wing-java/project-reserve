package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.UserBenefitService;
import com.ruoyi.project.develop.task.service.UserOrderService;

/**
 * 定时任务--结算订单任务
 */
@Component("userOrderTask")
public class UserOrderTask {

	@Autowired
	UserOrderService userOrderService;
	
	public void dealUserOrder() {
		userOrderService.dealUserOrder();
	}
	
	public void dealUserOrderSettle() {
		userOrderService.dealUserOrderSettle();
	}
}
