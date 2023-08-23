package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户意见反馈相关
 * @author Administrator
 *
 */
public interface UserFeedBackService {


	/**
	 * 用户意见反馈
	 * @param map
	 * @return
	 */
	R addUserFeedBack(Map<String, Object> map);

	
	/**
	 * 查询意见反馈列表
	 * @param map
	 * @return
	 */
	R getUserFeedBackList(Map<String, Object> map);


	/**
	 * 查询意见反馈详情
	 * @param map
	 * @return
	 */
	R getUserFeedBackDetail(Map<String, Object> map);

}
