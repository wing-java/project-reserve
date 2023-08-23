package com.example.longecological.service.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheVersionService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.annotations.EnableCacheVersionService.CacheVersionOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.mapper.product.SysProductMapper;
import com.example.longecological.mapper.product.SysProductOrderMapper;
import com.example.longecological.service.product.SysProductCacheService;

@Service
public class SysProductCacheServiceImpl implements SysProductCacheService {
	
	@Autowired
	SysProductMapper sysProductMapper;
	@Autowired
	SysProductOrderMapper sysProductOrderMapper;

	@Override
//	@EnableCacheVersionService(keyPrefix=RedisNameConstants.sys_product_list,
//	fieldKey="#map['lastIdKey']", 
//	versionKey=RedisNameVersionConstants.sys_product_list_version,
//	fieldVersionKey="",
//	cacheVersionOperation = CacheVersionOperation.QUERY,
//	expireTime=86400)
	public List<Map<String, Object>> getSysProductList(Map<String, Object> map) {
		return sysProductMapper.getSysProductList(map);
	}

	/**
	 * 查询地址详情
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.sys_product_detail,
//			fieldKey="#map['product_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysProductById(Map<String, Object> map) {
		return sysProductMapper.getSysProductById(map);
	}

	/**
	 * 查询产品订单详情
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.user_product_order_detail,
//			fieldKey="#map['order_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserProductOrderInfoById(Map<String, Object> map) {
		return sysProductOrderMapper.getUserProductOrderInfoById(map);
	}

	/**
	 * 查询产品订单列表
	 */
	@Override
//	@EnableCacheVersionService(keyPrefix=RedisNameConstants.user_product_order_list,
//	fieldKey="#map['sys_user_id'];#map['order_type'];#map['lastIdKey']", 
//	versionKey=RedisNameVersionConstants.user_product_order_list_version,
//	fieldVersionKey="#map['sys_user_id']",
//	cacheVersionOperation = CacheVersionOperation.QUERY,
//	expireTime=86400)
	public List<Map<String, Object>> getSysProductOrderList(Map<String, Object> map) {
		return sysProductOrderMapper.getSysProductOrderList(map);
	}

}
