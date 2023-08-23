package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 用户账户相关
 * @author Administrator
 *
 */
public interface UserAccountService {

	
	/**
	 * 查询用户账户信息列表
	 * @param map
	 * @return
	 */
	R getUserAccountList(Map<String, Object> map);
	/**
	 * 修改用户账户信息
	 * @param map
	 * @return
	 */
	R updateUserAccount(Map<String, Object> map);
	/**
	 * 根据id查询用户账户详情
	 * @param map
	 * @return
	 */
	R getUserAccountById(Map<String, Object> map);


	/**
	 * 查询用户账户信息
	 * @param map
	 * @return
	 */
	R getUserAccountInfo(Map<String, Object> map);
	/**
	 * 查询用户该类型的账户信息
	 * @param map
	 * @return
	 */
	R getUserAccountByType(Map<String, Object> map);

}
