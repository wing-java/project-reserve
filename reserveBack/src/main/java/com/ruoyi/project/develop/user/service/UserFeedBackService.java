package com.ruoyi.project.develop.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserFeedBack;

/**
 * 用户意见反馈管理
 * @author Administrator
 *
 */
public interface UserFeedBackService {

	
	/**
	 * 查询用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserFeedBackList(Map<String, Object> params);

	
	/**
	 * 导出用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<UserFeedBack> selectUserFeedBackList(Map<String, Object> params);


	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getUserFeedBackById(String id);


	/**
	 * 修改系统回复
	 * @param params
	 * @return
	 */
	R editUserFeedBack(Map<String, Object> params);
	
}
	
	
