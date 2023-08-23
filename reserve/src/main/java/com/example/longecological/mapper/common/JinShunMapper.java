package com.example.longecological.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface JinShunMapper {

	/**
	 * 查询金顺订单
	 * @param order_no
	 * @return
	 */
	Map<String, Object> getJinShunTradeByOrderNo(@Param("order_no") String order_no);
	
	/**
	 * 更新金顺订单状态
	 * @param map
	 * @return
	 */
	int updateOrderStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新充值订单状态
	 * @param map
	 * @return
	 */
	int updateRechargeOrderStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增
	 * @param map
	 * @return
	 */
	int insertJinShunTrade(@Param("map") Map<String, Object> map);
}
