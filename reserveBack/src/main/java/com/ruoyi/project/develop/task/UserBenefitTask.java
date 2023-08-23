package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.UserBenefitService;

/**
 * 定时任务--统计用户流水 每小时汇总一次
 */
@Component("userBenefitTask")
public class UserBenefitTask {

	@Autowired
	UserBenefitService userBenefitService;
	
	public void dealUserBenefit() {
		userBenefitService.dealUserBenefit();
	}
}
