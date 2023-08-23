package com.ruoyi.project.develop.pay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.pay.domain.UserTradeWechatpay;
import com.ruoyi.project.develop.pay.mapper.UserTradeWechatpayMapper;
import com.ruoyi.project.develop.pay.service.UserTradeWechatpayService;


/**
 * 线上微信充值管理
 * @author Administrator
 *
 */
@Service
public class UserTradeWechatpayServiceImpl implements UserTradeWechatpayService {

	@Autowired
	private UserTradeWechatpayMapper userTradeWechatpayMapper;
	
	
	/**
	 * 查询线上微信充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserTradeWechatpayList(Map<String, Object> params) {
		return userTradeWechatpayMapper.getUserTradeWechatpayList(params);
	}

	
	/**
	 * 导出线上微信充值列表
	 */
	@Override
	public List<UserTradeWechatpay> selectUserTradeWechatpayList(Map<String, Object> params) {
		return userTradeWechatpayMapper.selectUserTradeWechatpayList(params);
	}

	
	/**
	 * 根据订单编号查询订单详情
	 */
	@Override
	public Map<String, Object> getUserTradeWechatpayById(String id) {
		return userTradeWechatpayMapper.getUserTradeWechatpayById(id);
	}
	
}

