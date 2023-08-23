package com.example.longecological.service.common;

import java.util.List;
import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
public interface SysFunctionLockParamService {
	
	
	/**
	 * 根据参数代码查询参数开关值
	 * @param code
	 * @return
	 */
	String getFunctionLockParamByCode(String code);

	
	/**
	 * 查询系统功能开关参数值列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysFuctionLockParamList(Map<String, Object> map);

	
	/**
	 * 根据参数代码查询参数开关值
	 * @param map
	 * @return
	 */
	R getFunctionLockParamByCode(Map<String, Object> map);
	
}
