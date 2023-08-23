package com.ruoyi.project.develop.reward.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.reward.domain.UserRewardToAlgebra;
import com.ruoyi.project.develop.reward.mapper.UserRewardToAlgebraMapper;
import com.ruoyi.project.develop.reward.service.UserRewardToAlgebraService;

@Service
public class UserRewardToAlgebraServiceImpl implements UserRewardToAlgebraService {

	@Autowired
	private UserRewardToAlgebraMapper userRewardToAlgebraMapper;

	
	/**
	 * 查询
	 */
	@Override
	public List<Map<String, Object>> getUserRewardToAlgebraList(Map<String, Object> params) {
		return userRewardToAlgebraMapper.getUserRewardToAlgebraList(params);
	}

	
	/**
	 * 导出
	 */
	@Override
	public List<UserRewardToAlgebra> selectUserRewardToAlgebraList(Map<String, Object> params) {
		return userRewardToAlgebraMapper.selectUserRewardToAlgebraList(params);
	}
}
