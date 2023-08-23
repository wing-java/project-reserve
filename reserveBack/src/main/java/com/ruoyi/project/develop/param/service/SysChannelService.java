package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysChannel;

public interface SysChannelService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysChannelList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysChannel> selectSysChannelList(Map<String, Object> params);

	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysChannelById(String id);

	
	/**
	 * 编辑
	 * @param params
	 * @return
	 */
	R editSysChannel(Map<String, Object> params);

	
	/**
	 * 新增
	 * @param params
	 * @return
	 */
	R addSysChannel(Map<String, Object> params);


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	R batchRemoveSysChannel(String ids);
}
