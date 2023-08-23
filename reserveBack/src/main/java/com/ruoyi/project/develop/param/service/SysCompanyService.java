package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysCompany;

/**
 * 公司简介信息管理
 * @author Administrator
 *
 */
public interface SysCompanyService {
	
	/**
	 * 查询公司简介列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCompanyList(Map<String, Object> params);
	/**
	 * 导出公司简介列表
	 * @param params
	 * @return
	 */
	List<SysCompany> selectSysCompanyList(Map<String, Object> params);


	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCompanyById(String id);
	/**
	 * 编辑公司简介
	 * @param params
	 * @return
	 */
	R editSysCompany(Map<String, Object> map);

}
