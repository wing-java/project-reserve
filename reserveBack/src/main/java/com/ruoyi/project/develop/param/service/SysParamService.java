package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysParam;

/**
 * 参数管理
 * @author Administrator
 *
 */
public interface SysParamService {
	
	
	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getParamByCode(String code);

	
	/**
	 * 查询系统参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysParamList(Map<String, Object> params);

	
	/**
	 * 导出系统参数
	 * @param params
	 * @return
	 */
	List<SysParam> selectSysParamList(Map<String, Object> params);


	/**
	 * 根据参数id查询参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysParamById(String id);


	/**
	 * 编辑系统参数
	 * @param params
	 * @return
	 */
	R editSysParam(Map<String, Object> map);
}
