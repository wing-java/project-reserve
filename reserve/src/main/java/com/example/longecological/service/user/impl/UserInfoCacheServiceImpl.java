package com.example.longecological.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.annotations.EnableCacheVersionService;
import com.example.longecological.annotations.EnableCacheVersionService.CacheVersionOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.mapper.user.UserFeedBackMapper;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.user.UserInfoCacheService;

@Service
public class UserInfoCacheServiceImpl implements UserInfoCacheService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserFeedBackMapper userFeedBackMapper;
	
	
	/**
	 * 通过用户ID查询用户缓存信息
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.user_info_id,
			fieldKey="#user_id", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserInfoCacheById(String user_id) {
		return userInfoMapper.getUserInfoCacheById(user_id);
	}
	/**
	 * 通过用户ID查询冻结用户信息
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.user_freeze,
			fieldKey="#user_id", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserFreezeCacheById(String user_id){
		return userInfoMapper.getUserFreezeCacheById(user_id);
	}

	
	/**
	 * 查询意见反馈列表
	 */
	@EnableCacheVersionService(keyPrefix=RedisNameConstants.user_feedback_list,
			fieldKey="#map['userIdKey'];#map['lastIdKey']", 
			versionKey=RedisNameVersionConstants.user_feedback_list_version,
			fieldVersionKey="#map['userIdKey']",
			cacheVersionOperation = CacheVersionOperation.QUERY,
			expireTime=86400)
	@Override
	public List<Map<String, Object>> getUserFeedBackList(Map<String, Object> map) {
		return userFeedBackMapper.getUserFeedBackList(map);
	}
	/**
	 * 查询意见反馈详情
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.user_feedback_id,
			fieldKey="#map['feedbackIdKey']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserFeedBackDetail(Map<String, Object> map) {
		return userFeedBackMapper.getUserFeedBackDetail(map);
	}
	
}
