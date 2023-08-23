package com.example.longecological.service.product;

import java.util.Map;

import com.example.longecological.entity.R;

public interface SysProductService {

	/**
	 * 查询平台产品列表
	 * @param map
	 * @return
	 */
	R getSysProductList(Map<String, Object> map);
	
	/**
	 * 查询平台产品详情
	 * @param map
	 * @return
	 */
	R getSysProductDetail(Map<String, Object> map);
}
