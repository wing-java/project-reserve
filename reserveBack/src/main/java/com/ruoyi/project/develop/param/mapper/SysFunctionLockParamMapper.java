package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysFunctionLockParam;



/**
 * 系统开关参数管理
 * @author Administrator
 *
 */
public interface SysFunctionLockParamMapper {

	/**
	 * 根据参数代码查询系统开关参数值
	 * @param code
	 * @return
	 */
	String getFunctionLockParamByCode(@Param("code") String code);

	
	/**
	 * 查询系统开关参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysFunctionLockParamList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统开关参数
	 * @param params
	 * @return
	 */
	List<SysFunctionLockParam> selectSysFunctionLockParamList(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数id查询系统开关参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysFunctionLockParamById(@Param("param_id") String id);


	/**
	 * 更新系统开关参数信息
	 * @param params
	 * @return
	 */
	int updateSysFunctionLockParam(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数代码查询系统开关参数详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getFunctionLockParamMapByCode(@Param("code") String code);
	
}
