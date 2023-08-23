package com.ruoyi.project.develop.pay.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.pay.domain.UserTradeWechatpay;

/**
 * 线上微信充值管理
 * @author Administrator
 *
 */
public interface UserTradeWechatpayService {

	
	/**
	 * 查询线上微信充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeWechatpayList(Map<String, Object> params);

	
	/**
	 * 导出线上微信充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeWechatpay> selectUserTradeWechatpayList(Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeWechatpayById(String id);

}
	
	
