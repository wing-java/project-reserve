package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;

public interface UserSignService {

	/**
	 * 查询签到列表
	 * @param map
	 * @return
	 */
	R getUserSignList(Map<String, Object> map);
	
	/**
	 * 用户签到
	 * @param map
	 * @return
	 */
	R userSign(Map<String, Object> map);
}
