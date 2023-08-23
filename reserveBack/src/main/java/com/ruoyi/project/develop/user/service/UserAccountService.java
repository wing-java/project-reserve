package com.ruoyi.project.develop.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserAccount;

/**
 * 用户收款账户管理
 * @author Administrator
 *
 */
public interface UserAccountService {

	
	/**
	 * 查询收款账户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserAccountList(Map<String, Object> params);

	
	/**
	 * 导出用户收款账户列表
	 * @param params
	 * @return
	 */
	List<UserAccount> selectUserAccountList(Map<String, Object> params);

}
	
	
