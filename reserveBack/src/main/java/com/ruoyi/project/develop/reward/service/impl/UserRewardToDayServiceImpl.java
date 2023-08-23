package com.ruoyi.project.develop.reward.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.reward.domain.UserRewardToDay;
import com.ruoyi.project.develop.reward.mapper.UserRewardToDayMapper;
import com.ruoyi.project.develop.reward.service.UserRewardToDayService;

@Service
public class UserRewardToDayServiceImpl implements UserRewardToDayService {

	@Autowired
	private UserRewardToDayMapper userRewardToDayMapper;

	
	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getUserRewardToDayList(Map<String, Object> params) {
		return userRewardToDayMapper.getUserRewardToDayList(params);
	}

	
	/**
	 * 导出
	 */
	@Override
	public List<UserRewardToDay> selectUserRewardToDayList(Map<String, Object> params) {
		return userRewardToDayMapper.selectUserRewardToDayList(params);
	}
}
