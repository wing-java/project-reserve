package com.ruoyi.project.develop.pay.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.pay.domain.UserTradeCike;

public interface UserTradeCikeService {

	/**
	 * 查询线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeCikeList(Map<String, Object> params);

	
	/**
	 * 导出线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeCike> selectUserTradeCikeList(Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeCikeById(String id);
}
