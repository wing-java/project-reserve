package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysCompany;


/**
 * 系统快递管理
 * @author Administrator
 *
 */
public interface SysCompanyMapper {

	
	/**
	 * 查询公司简介列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCompanyList(@Param("map") Map<String, Object> params);
	/**
	 * 导出地区
	 * @param params
	 * @return
	 */
	List<SysCompany> selectSysCompanyList(@Param("map") Map<String, Object> params);
	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCompanyById(@Param("company_id") String company_id);


	/**
	 * 更新地区
	 * @param params
	 * @return
	 */
	int updateSysCompany(@Param("map") Map<String, Object> params);
}
