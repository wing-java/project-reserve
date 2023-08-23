package com.example.longecological.mapper.wallet;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserWalletMapper {

	/**
	 * 更新钱包数量
	 * @param map
	 * @return
	 */
	int updateUserWalletNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询钱包信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserWalletInfo(@Param("user_id") String user_id);
}
