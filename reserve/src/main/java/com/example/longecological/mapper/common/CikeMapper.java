package com.example.longecological.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CikeMapper {

	/**
	 * 查询刺客订单
	 * @param order_no
	 * @return
	 */
	Map<String, Object> getCikeTradeByOrderNo(@Param("order_no") String order_no);
	
	/**
	 * 更新刺客订单状态
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
	int insertCikeTrade(@Param("map") Map<String, Object> map);
}
