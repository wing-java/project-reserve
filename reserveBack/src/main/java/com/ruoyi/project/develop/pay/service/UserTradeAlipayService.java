package com.ruoyi.project.develop.pay.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.pay.domain.UserTradeAlipay;

/**
 * 线上支付宝充值管理
 * @author Administrator
 *
 */
public interface UserTradeAlipayService {

	
	/**
	 * 查询线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeAlipayList(Map<String, Object> params);

	
	/**
	 * 导出线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeAlipay> selectUserTradeAlipayList(Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeAlipayById(String id);

}
	
	
