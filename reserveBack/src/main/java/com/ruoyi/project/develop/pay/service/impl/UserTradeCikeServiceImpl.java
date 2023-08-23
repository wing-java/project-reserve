package com.ruoyi.project.develop.pay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.pay.domain.UserTradeCike;
import com.ruoyi.project.develop.pay.mapper.UserTradeCikeMapper;
import com.ruoyi.project.develop.pay.service.UserTradeCikeService;

@Service
public class UserTradeCikeServiceImpl implements UserTradeCikeService {

	@Autowired
	private UserTradeCikeMapper userTradeCikeMapper;
	
	
	/**
	 * 查询线上支付宝充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserTradeCikeList(Map<String, Object> params) {
		return userTradeCikeMapper.getUserTradeCikeList(params);
	}

	
	/**
	 * 导出线上支付宝充值列表
	 */
	@Override
	public List<UserTradeCike> selectUserTradeCikeList(Map<String, Object> params) {
		return userTradeCikeMapper.selectUserTradeCikeList(params);
	}

	
	/**
	 * 根据订单编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserTradeCikeById(String id) {
		return userTradeCikeMapper.getUserTradeCikeById(id);
	}
}
