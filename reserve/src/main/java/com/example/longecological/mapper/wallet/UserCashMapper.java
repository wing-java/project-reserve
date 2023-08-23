package com.example.longecological.mapper.wallet;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户账户管理
 * @author Administrator
 *
 */
public interface UserCashMapper {

	/**
	 * 查询用户提现记录列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserCashList(@Param("map") Map<String, Object> map);
	/**
	 * 查询用户提现记录详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserCashDetail(@Param("map") Map<String, Object> map);

	
	/**
	 * 记录用户提现记录
	 * @param dataMap
	 * @return
	 */
	int addUserCash(@Param("map") Map<String, Object> dataMap);
	/**
	 * 记录提现记录详情
	 * @param dataMap
	 * @return
	 */
	int addUserCashDetail(@Param("map") Map<String, Object> dataMap);
	
	/**
	 * 查询当天提现笔数
	 * @param user_id
	 * @param date
	 * @return
	 */
	int getUserCashByDate(@Param("user_id") String user_id, @Param("date") String date);
	
}
