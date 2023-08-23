package com.ruoyi.project.develop.reward.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.reward.domain.UserRewardToYear;
import com.ruoyi.project.develop.reward.mapper.UserRewardToYearMapper;
import com.ruoyi.project.develop.reward.service.UserRewardToYearService;

@Service
public class UserRewardToYearServiceImpl implements UserRewardToYearService {

	@Autowired
	private UserRewardToYearMapper userRewardToYearMapper;

	
	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getUserRewardToYearList(Map<String, Object> params) {
		return userRewardToYearMapper.getUserRewardToYearList(params);
	}

	
	/**
	 * 导出
	 */
	@Override
	public List<UserRewardToYear> selectUserRewardToYearList(Map<String, Object> params) {
		return userRewardToYearMapper.selectUserRewardToYearList(params);
	}
}
