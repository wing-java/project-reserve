package com.ruoyi.project.develop.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.product.domain.UserProductOrder;



/**
 * 订单信息管理
 * @author Administrator
 *
 */
public interface UserProductOrderMapper {
	
	/**
	 * 查询订单信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserProductOrderList(@Param("map") Map<String, Object> params);
	/**
	 * 导出订单信息列表
	 * @param params
	 * @return
	 */
	List<UserProductOrder> selectUserProductOrderList(@Param("map") Map<String, Object> params);
	/**
	 * 根据编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserProductOrderById(@Param("id") String id);
	/**
	 * 根据订单号查询订单详情
	 * @param ProductOrder_id
	 * @return
	 */
	Map<String, Object> getUserProductOrderByProductOrderId(@Param("order_no") String order_no);
	

}
