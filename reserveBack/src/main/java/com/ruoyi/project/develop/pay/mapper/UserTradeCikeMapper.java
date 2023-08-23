package com.ruoyi.project.develop.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.pay.domain.UserTradeCike;

public interface UserTradeCikeMapper {

	/**
	 * 查询线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserTradeCikeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上支付宝充值列表
	 * @param params
	 * @return
	 */
	List<UserTradeCike> selectUserTradeCikeList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 根据订单编号查询订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserTradeCikeById(@Param("id") String id);
}
