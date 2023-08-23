package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;

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
	 * 根据参数代码查询参数值
	 * @param map
	 * @return
	 */
	R getParamByCode(Map<String, Object> map);
	
}
