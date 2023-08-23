package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户账户管理
 * @author Administrator
 *
 */
public interface UserAccountMapper {

	/**
	 * 查询用户账户列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserAccountList(@Param("map") Map<String, Object> map);

	
	/**
	 * 将用户所有账户均设置成非默认账户
	 * @param map
	 * @return
	 */
	int updateAccountNoDefault(@Param("map") Map<String, Object> map);


	/**
	 * 添加用户账户（默认和非默认方法都通用）
	 * @param map
	 * @return
	 */
	int addUserAccount(@Param("map") Map<String, Object> map);


	/**
	 * 编辑用户账户（默认和非默认方法都通用）
	 * @param map
	 * @return
	 */
	int updateUserAccount(@Param("map") Map<String, Object> map);


	/**
	 * 删除用户账户
	 * @param map
	 * @return
	 */
	int deleteUserAccountById(@Param("map") Map<String, Object> map);


	/**
	 * 根据地址id查询用户账户
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserAccountById(@Param("map") Map<String, Object> map);


	/**
	 * 删除用户提现卡
	 * @param string
	 * @return
	 */
	int deleteUserAccountByUserId(@Param("sys_user_id") String sys_user_id);


	/**
	 * 查询用户的收款账户数量
	 * @param map
	 * @return
	 */
	int getUserAccountNum(@Param("map") Map<String, Object> map);


	/**
	 * 查询用户账户信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserAccountInfo(@Param("map") Map<String, Object> map);


	/**
	 * 查询用户该类型的账户信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserAccountByType(@Param("map") Map<String, Object> map);

}
