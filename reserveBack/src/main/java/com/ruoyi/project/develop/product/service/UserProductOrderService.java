package com.ruoyi.project.develop.product.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.product.domain.UserProductOrder;

/**
 * 订单信息管理
 * @author Administrator
 *
 */
public interface UserProductOrderService {
	
	/**
	 * 查询系统订单列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserProductOrderList(Map<String, Object> params);
	/**
	 * 导出系统订单列表
	 * @param params
	 * @return
	 */
	List<UserProductOrder> selectUserProductOrderList(Map<String, Object> params);
	/**
	 * 根据编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserProductOrderById(String id);
	/**
	 * 根据订单号查询订单详情
	 * @param ProductOrder_id
	 * @return
	 */
	Map<String, Object> getUserProductOrderByProductOrderId(String ProductOrder_id);

}