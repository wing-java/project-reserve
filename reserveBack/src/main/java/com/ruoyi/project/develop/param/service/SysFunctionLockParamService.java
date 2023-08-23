package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysFunctionLockParam;

/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
public interface SysFunctionLockParamService {
	
	
	/**
	 * 根据参数代码查询系统开关参数值
	 * @param code
	 * @return
	 */
	String getFunctionLockParamByCode(String code);

	
	/**
	 * 查询系统开关参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysFunctionLockParamList(Map<String, Object> params);

	
	/**
	 * 导出系统开关参数
	 * @param params
	 * @return
	 */
	List<SysFunctionLockParam> selectSysFunctionLockParamList(Map<String, Object> params);


	/**
	 * 根据参数id查询系统开关参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysFunctionLockParamById(String id);


	/**
	 * 编辑系统开关参数
	 * @param params
	 * @return
	 */
	R editSysFunctionLockParam(Map<String, Object> map);
}
