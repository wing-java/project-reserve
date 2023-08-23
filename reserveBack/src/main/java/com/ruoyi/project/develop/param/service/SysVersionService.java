package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysVersion;

/**
 * 系统版本信息管理
 * @author Administrator
 *
 */
public interface SysVersionService {
	
	/**
	 * 查询系统版本列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysVersionList(Map<String, Object> params);

	
	/**
	 * 导出系统版本列表
	 * @param params
	 * @return
	 */
	List<SysVersion> selectSysVersiontList(Map<String, Object> params);


	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysVersionById(String id);


	/**
	 * 编辑系统版本
	 * @param params
	 * @return
	 */
	R editSysVersion(Map<String, Object> map);


	/**
	 * 新增保存系统版本
	 * @param params
	 * @return
	 */
	R addSysVersion(Map<String, Object> params);


	/**
	 * 批量删除系统版本
	 * @param ids
	 * @return
	 */
	R batchRemoveSysVersion(String ids);

}
