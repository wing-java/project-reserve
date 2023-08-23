package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysChannel;

public interface SysChannelMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysChannelList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysChannel> selectSysChannelList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysChannelById(@Param("channel_id") String Channel_id);


	/**
	 * 更新
	 * @param params
	 * @return
	 */
	int updateSysChannel(@Param("map") Map<String, Object> params);


	/**
	 * 新增
	 * @param params
	 * @return
	 */
	int addSysChannel(@Param("map") Map<String, Object> params);


	/**
	 * 
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteSysChannel(@Param("channel_id") String Channel_id);
}
