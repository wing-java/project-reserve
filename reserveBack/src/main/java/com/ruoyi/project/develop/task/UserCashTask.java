package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.UserCashTaskService;

/**
 * 定时任务--查询提现状态
 */
@Component("userCashTask")
public class UserCashTask {

	@Autowired
	UserCashTaskService userCashTaskService;
	
	public void dealUserCashTaskSatus() {
		userCashTaskService.dealUserCashTaskSatus();
	}
}
