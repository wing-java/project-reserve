package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统新闻管理
 * @author Administrator
 *
 */
public interface SysNewsMapper {

	
	/**
	 * 查询系统最新新闻
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getNewNews(@Param("map") Map<String, Object> dataMap);

	
	/**
	 * 查询系统新闻列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysNewsList(@Param("map") Map<String, Object> map);


	/**
	 * 查询系统新闻详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysNewsDetail(@Param("map") Map<String, Object> map);

	
}
