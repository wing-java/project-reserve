package com.example.longecological.service.product;

import java.util.List;
import java.util.Map;

public interface SysProductCacheService {

	/**
	 * 查询产品列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysProductList(Map<String, Object> map);
	
	/**
	 * 查询产品详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysProductById(Map<String, Object> map);
	
	/**
	 * 查询产品订单详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserProductOrderInfoById(Map<String, Object> map);
	
	/**
	 * 查询产品订单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysProductOrderList(Map<String, Object> map);
}
