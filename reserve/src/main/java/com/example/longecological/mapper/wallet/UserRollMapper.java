package com.example.longecological.mapper.wallet;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 转账记录信息管理
 * @author Administrator
 *
 */
public interface UserRollMapper {
	
	/**
	 * 记录用户转账记录
	 * @param rollOrderMap
	 * @return
	 */
	int addUserRoll(@Param("map") Map<String, Object> rollOrderMap);

	
	/**
	 * 查询转出订单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserRollOutList(@Param("map") Map<String, Object> map);
	/**
	 * 查询转入订单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserRollInList(@Param("map") Map<String, Object> map);


}
