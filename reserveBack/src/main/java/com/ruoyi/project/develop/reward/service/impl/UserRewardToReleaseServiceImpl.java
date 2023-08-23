package com.ruoyi.project.develop.reward.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.reward.domain.UserRewardToRelease;
import com.ruoyi.project.develop.reward.mapper.UserRewardToReleaseMapper;
import com.ruoyi.project.develop.reward.service.UserRewardToReleaseService;

@Service
public class UserRewardToReleaseServiceImpl implements UserRewardToReleaseService {

	@Autowired
	private UserRewardToReleaseMapper userRewardToReleaseMapper;

	
	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getUserRewardToReleaseList(Map<String, Object> params) {
		return userRewardToReleaseMapper.getUserRewardToReleaseList(params);
	}

	
	/**
	 * 导出
	 */
	@Override
	public List<UserRewardToRelease> selectUserRewardToReleaseList(Map<String, Object> params) {
		return userRewardToReleaseMapper.selectUserRewardToReleaseList(params);
	}
}
