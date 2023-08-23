package com.example.longecological.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
public interface SysFunctionLockParamMapper {

	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getFunctionLockParamByCode(@Param("code") String code);
	
	
	/**
	 * 查询系统功能开关参数值列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysFuctionLockParamList(@Param("map") Map<String, Object> map);
	
}
