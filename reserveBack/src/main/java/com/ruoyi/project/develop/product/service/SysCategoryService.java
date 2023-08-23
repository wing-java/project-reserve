package com.ruoyi.project.develop.product.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.product.domain.SysCategory;

public interface SysCategoryService {

	/**
	 * 查询产品包列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCategoryList(Map<String, Object> params);
	/**
	 * 导出产品包列表
	 * @param params
	 * @return
	 */
	List<SysCategory> selectSysCategoryList(Map<String, Object> params);
	/**
	 * 根据编号查询产品包详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCategoryById(String id);
	

	/**
	 * 新增产品包
	 * @param params
	 * @return
	 */
	R addSysCategory(Map<String, Object> params);
	/**
	 * 修改保存产品包
	 * @param params
	 * @return
	 */
	R editSysCategory(Map<String, Object> params);
	/**
	 * 修改排序
	 * @param params
	 * @return
	 */
	R sortSysCategory(Map<String, Object> params);
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	R batchRemoveSysCategory(String ids);
}
