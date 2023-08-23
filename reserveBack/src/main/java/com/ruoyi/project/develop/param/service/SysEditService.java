package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.param.domain.SysEdit;

/**
 * 参数修改记录管理
 * @author Administrator
 *
 */
public interface SysEditService {
	
	
	/**
	 * 查询系统参数修改记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysEditList(Map<String, Object> params);

	
	/**
	 * 导出系统参数修改记录
	 * @param params
	 * @return
	 */
	List<SysEdit> selectSysEditList(Map<String, Object> params);


	/**
	 * 根据id查询参数修改记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysEditById(String id);

}
