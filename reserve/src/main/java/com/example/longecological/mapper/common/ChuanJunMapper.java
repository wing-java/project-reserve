package com.example.longecological.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ChuanJunMapper {

	/**
	 * 保存川军记录
	 * @param map
	 * @return
	 */
	int insertChuanJunTrade(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询川军支付订单
	 * @param order_no
	 * @return
	 */
	Map<String, Object> getChuanJunTradeByOrderNo(@Param("order_no") String order_no);
	
	/**
	 * 更新订单状态
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
}
