package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysVersion;



/**
 * 版本信息管理
 * @author Administrator
 *
 */
public interface SysVersionMapper {

	
	/**
	 * 查询系统版本列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysVersionList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统版本
	 * @param params
	 * @return
	 */
	List<SysVersion> selectSysVersiontList(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysVersionById(@Param("version_id") String version_id);


	/**
	 * 更新版本信息
	 * @param params
	 * @return
	 */
	int updateSysVersion(@Param("map") Map<String, Object> params);


	/**
	 * 新增版本信息
	 * @param map
	 * @return
	 */
	int addSysVersion(@Param("map") Map<String, Object> map);


	/**
	 * 删除系统版本
	 * @param sysVersionId
	 * @return
	 */
	int deleteSysVersion(@Param("version_id") String version_id);
	
}
