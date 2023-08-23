package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysChannelMapper {

	/**
	 * 查询渠道详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysChannelById(@Param("id") String id);
	
	/**
	 * 查询列表
	 * @return
	 */
	List<Map<String, Object>> getSysChannelList();
}
