package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.BackOrDealDealService;

/**
 * 定时任务--备份或者清空用户数据
 * 
 * @author ruoyi
 */
@Component("backOrClearTask")
public class BackOrClearTask {

	@Autowired
	private BackOrDealDealService backOrDealDealService;

	
	/**
	 * 备份短信验证码
	 */
	public void backUpVerifyRecord(){
		//备份短信验证码
		backOrDealDealService.backUpVerifyRecord();
	}
	
	/**
	 * 
	 */
	public void confirm(){
		//备份短信验证码
		backOrDealDealService.backUpVerifyRecord();
	}
	

}
