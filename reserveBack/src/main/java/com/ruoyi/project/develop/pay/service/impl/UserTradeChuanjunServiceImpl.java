package com.ruoyi.project.develop.pay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.pay.domain.UserTradeChuanjun;
import com.ruoyi.project.develop.pay.mapper.UserTradeChuanjunMapper;
import com.ruoyi.project.develop.pay.service.UserTradeChuanjunService;

@Service
public class UserTradeChuanjunServiceImpl implements UserTradeChuanjunService {

	@Autowired
	private UserTradeChuanjunMapper userTradeChuanjunMapper;
	
	
	/**
	 * 查询线上支付宝充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserTradeChuanjunList(Map<String, Object> params) {
		return userTradeChuanjunMapper.getUserTradeChuanjunList(params);
	}

	
	/**
	 * 导出线上支付宝充值列表
	 */
	@Override
	public List<UserTradeChuanjun> selectUserTradeChuanjunList(Map<String, Object> params) {
		return userTradeChuanjunMapper.selectUserTradeChuanjunList(params);
	}

	
	/**
	 * 根据订单编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserTradeChuanjunById(String id) {
		return userTradeChuanjunMapper.getUserTradeChuanjunById(id);
	}
}
