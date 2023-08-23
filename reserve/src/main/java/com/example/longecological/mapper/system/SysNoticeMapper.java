package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统公告管理
 * @author Administrator
 *
 */
public interface SysNoticeMapper {

	
	/**
	 * 查询系统最新版本
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getNewNotice(@Param("map") Map<String, Object> dataMap);

	
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysNoticeList(@Param("map") Map<String, Object> map);


	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysNoticeDetail(@Param("map") Map<String, Object> map);

	
}
