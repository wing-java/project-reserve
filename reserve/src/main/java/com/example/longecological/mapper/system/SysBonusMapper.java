package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysBonusMapper {

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysBonusList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysBonusDetail(@Param("map") Map<String, Object> map);
}
