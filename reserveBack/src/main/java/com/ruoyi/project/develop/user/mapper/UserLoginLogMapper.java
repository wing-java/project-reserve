package com.ruoyi.project.develop.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.user.domain.UserLoginLog;



/**
 * 用户登录日志信息管理
 * @author Administrator
 *
 */
public interface UserLoginLogMapper {


	/**
	 * 查询用户登录日志列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserLoginLogList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户登录日志列表
	 * @param params
	 * @return
	 */
	List<UserLoginLog> selectUserLoginLogList(@Param("map") Map<String, Object> params);

}
