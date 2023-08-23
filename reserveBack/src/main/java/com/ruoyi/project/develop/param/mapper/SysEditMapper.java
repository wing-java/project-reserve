package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysEdit;



/**
 * 参数修改记录管理
 * @author Administrator
 *
 */
public interface SysEditMapper {

	
	/**
	 * 查询系统参数修改记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysEditList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统参数修改记录
	 * @param params
	 * @return
	 */
	List<SysEdit> selectSysEditList(@Param("map") Map<String, Object> params);

	
	/**
	 * 记录系统修改参数记录
	 * @param params
	 * @return
	 */
	int addSysEdit(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询修改参数记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysEditById(@Param("id") String id);
	
}
