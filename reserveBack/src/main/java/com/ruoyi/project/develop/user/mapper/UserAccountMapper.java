package com.ruoyi.project.develop.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.user.domain.UserAccount;



/**
 * 用户收款账户信息管理
 * @author Administrator
 *
 */
public interface UserAccountMapper {


	/**
	 * 查询用户收款账户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserAccountList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户收款账户列表
	 * @param params
	 * @return
	 */
	List<UserAccount> selectUserAccountList(@Param("map") Map<String, Object> params);

}
