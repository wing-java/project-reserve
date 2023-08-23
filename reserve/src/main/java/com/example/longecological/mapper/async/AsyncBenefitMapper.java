package com.example.longecological.mapper.async;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AsyncBenefitMapper {

	/**
	 * 查询阶段收益比例
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserOrderDayBenefitRate(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新待领取收益
	 * @param map
	 * @return
	 */
	int updateOrderUnclaimedBenefit(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录阶段收益
	 * @param map
	 * @return
	 */
	int addUserRewardToDay(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询年收益比例
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserOrderYearBenefitRate(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录年收益
	 * @param map
	 * @return
	 */
	int addUserRewardToYear(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询层级收益列表
	 * @param parent_chain
	 * @return
	 */
	List<Map<String, Object>> getUserOrderAlgebraList(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录分享收益
	 * @param map
	 * @return
	 */
	int addUserRewardToAlgebra(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新订单状态
	 * @param map
	 * @return
	 */
	int updateOrderStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserOrderById(@Param("id") String id);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int updateOrderIsEnd(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新年度收益
	 * @param map
	 * @return
	 */
	int updateOrderYearBenefit(@Param("map") Map<String, Object> map);
}
