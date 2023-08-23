package com.ruoyi.project.develop.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.BackOrClearDealMapper;
import com.ruoyi.project.develop.task.service.BackOrDealDealService;


/**
 * 备份或者清除数据
 * @author Administrator
 *
 */
@Service
public class BackOrClearDealServiceImpl implements BackOrDealDealService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BackOrClearDealServiceImpl.class);
	
	@Autowired
	private BackOrClearDealMapper backOrClearDealMapper;
	
	
	/**
	 * 备份短信验证码
	 */
	@Override
	public void backUpVerifyRecord() {
		LOGGER.info("开始备份验证码");
		String date = TimeUtil.dayStart();
		backOrClearDealMapper.backUpVerifyRecord(date);
		backOrClearDealMapper.delVerifyRecord(date);
		LOGGER.info("验证码备份结束");
	}

}
