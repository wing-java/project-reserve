package com.ruoyi.project.develop.pay.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.pay.domain.UserTradeChuanjun;

public interface UserTradeChuanjunService {

	/**
	 * 查询线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeChuanjunList(Map<String, Object> params);

	
	/**
	 * 导出线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeChuanjun> selectUserTradeChuanjunList(Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeChuanjunById(String id);
}
