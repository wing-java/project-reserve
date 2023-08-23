package com.ruoyi.project.develop.pay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.pay.domain.UserTradeAlipay;
import com.ruoyi.project.develop.pay.mapper.UserTradeAlipayMapper;
import com.ruoyi.project.develop.pay.service.UserTradeAlipayService;


/**
 * 线上支付宝充值管理
 * @author Administrator
 *
 */
@Service
public class UserTradeAlipayServiceImpl implements UserTradeAlipayService {

	@Autowired
	private UserTradeAlipayMapper userTradeAlipayMapper;
	
	
	/**
	 * 查询线上支付宝充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserTradeAlipayList(Map<String, Object> params) {
		return userTradeAlipayMapper.getUserTradeAlipayList(params);
	}

	
	/**
	 * 导出线上支付宝充值列表
	 */
	@Override
	public List<UserTradeAlipay> selectUserTradeAlipayList(Map<String, Object> params) {
		return userTradeAlipayMapper.selectUserTradeAlipayList(params);
	}

	
	/**
	 * 根据订单编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserTradeAlipayById(String id) {
		return userTradeAlipayMapper.getUserTradeAlipayById(id);
	}
	
}

