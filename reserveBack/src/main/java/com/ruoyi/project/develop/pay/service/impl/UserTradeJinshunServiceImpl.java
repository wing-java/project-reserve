package com.ruoyi.project.develop.pay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.pay.domain.UserTradeJinshun;
import com.ruoyi.project.develop.pay.domain.UserTradeJinshun;
import com.ruoyi.project.develop.pay.mapper.UserTradeJinshunMapper;
import com.ruoyi.project.develop.pay.service.UserTradeJinshunService;

@Service
public class UserTradeJinshunServiceImpl implements UserTradeJinshunService {

	@Autowired
	private UserTradeJinshunMapper userTradeJinshunMapper;
	
	
	/**
	 * 查询线上支付宝充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserTradeJinshunList(Map<String, Object> params) {
		return userTradeJinshunMapper.getUserTradeJinshunList(params);
	}

	
	/**
	 * 导出线上支付宝充值列表
	 */
	@Override
	public List<UserTradeJinshun> selectUserTradeJinshunList(Map<String, Object> params) {
		return userTradeJinshunMapper.selectUserTradeJinshunList(params);
	}

	
	/**
	 * 根据订单编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserTradeJinshunById(String id) {
		return userTradeJinshunMapper.getUserTradeJinshunById(id);
	}

}
