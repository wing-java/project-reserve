package com.example.longecological.service.product;

import java.util.Map;

import com.example.longecological.entity.R;

public interface SysProductOrderService {

	/**
	 * 提交订单
	 * @param map
	 * @return
	 */
	R submitOrder(Map<String, Object> map);
	
	/**
	 * 查询订单详情
	 * @param map
	 * @return
	 */
	R getUserProductOrderDetail(Map<String, Object> map);
	
	/**
	 * 查询产品订单列表
	 * @param map
	 * @return
	 */
	R getSysProductOrderList(Map<String, Object> map);
	
	/**
	 * 领取收益
	 * @param map
	 * @return
	 */
	R receiveUnclaimedBenefit(Map<String, Object> map);
	
}
