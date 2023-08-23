package com.ruoyi.project.develop.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.user.domain.UserReal;

public interface UserRealMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserRealList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<UserReal> selectUserRealList(@Param("map") Map<String, Object> params);
	
	/**
	 * 详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getUserRealById(@Param("id") String id);
	
	/**
	 * 审核更新
	 * @param params
	 * @return
	 */
	int checkUserAgentInfo(@Param("map") Map<String, Object> params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	int updateUserAuthStatus(@Param("map") Map<String, Object> params);
}
