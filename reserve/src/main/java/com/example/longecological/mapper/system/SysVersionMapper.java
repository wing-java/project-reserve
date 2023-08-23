package com.example.longecological.mapper.system;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统版本管理
 * @author Administrator
 *
 */
public interface SysVersionMapper {

	
	/**
	 * 查询系统最新版本
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getNewVersion(@Param("map") Map<String, Object> dataMap);

	
}
