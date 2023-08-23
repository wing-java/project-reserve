package com.example.longecological.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysProductMapper {

	/**
	 * 查询产品列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysProductList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询产品详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysProductById(@Param("map") Map<String, Object> map);
}
