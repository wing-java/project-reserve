package com.ruoyi.project.develop.product.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.product.domain.SysProduct;

/**
 * 产品包管理
 * @author Administrator
 *
 */
public interface SysProductService {
	
	/**
	 * 查询产品包列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysProductList(Map<String, Object> params);
	/**
	 * 导出产品包列表
	 * @param params
	 * @return
	 */
	List<SysProduct> selectSysProductList(Map<String, Object> params);
	/**
	 * 根据编号查询产品包详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysProductById(String id);
	

	/**
	 * 新增产品包
	 * @param params
	 * @return
	 */
	R addSysProduct(Map<String, Object> params);
	/**
	 * 修改保存产品包
	 * @param params
	 * @return
	 */
	R editSysProduct(Map<String, Object> params);
	
	
	/**
	 * 批量删除恢复产品包
	 * @param params
	 * @return
	 */
	R batchSysDelSysProduct(Map<String, Object> params);
	/**
	 * 批量上下架产品包
	 * @param params
	 * @return
	 */
	R batchSysReleaseSysProduct(Map<String, Object> params);
	
	/**
	 * 修改排序
	 * @param params
	 * @return
	 */
	R sortSysProduct(Map<String, Object> params);

}