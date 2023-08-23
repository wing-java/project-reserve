package com.ruoyi.project.develop.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.user.domain.UserFeedBack;



/**
 * 用户意见反馈信息管理
 * @author Administrator
 *
 */
public interface UserFeedBackMapper {


	/**
	 * 查询用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserFeedBackList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<UserFeedBack> selectUserFeedBackList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserFeedBackById(@Param("feed_back_id") String feed_back_id);


	/**
	 * 更新系统回复
	 * @param params
	 * @return
	 */
	int updateUserFeedBack(@Param("map") Map<String, Object> params);
	
}
