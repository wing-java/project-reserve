package com.example.longecological.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserFeedBackMapper;
import com.example.longecological.service.user.UserFeedBackService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户意见反馈相关
 * @author Administrator
 *
 */
@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFeedBackServiceImpl.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	UserFeedBackMapper userFeedBackMapper;
	
	@Autowired
	UserInfoCacheService userInfoCacheService;
	
	
	/**
	 * 用户意见反馈
	 */
	@Override
	public R addUserFeedBack(Map<String, Object> map) {
		try {
			if(StringUtil.containString(StringUtil.getMapValue(map, "feedback_content"), "script")) {
				return R.error(CommonCodeConstant.COMMON_CODE_999987, CommonCodeConstant.COMMON_MSG_999987);
			}
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			System.out.println(map);
			int i = userFeedBackMapper.addUserFeedBack(map);
			if(i != 1) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999782, UserInfoCodeConstant.USER_INFO_MSG_999782);
			}
			//清除缓存
			redisUtils.updateVersion(RedisNameVersionConstants.user_feedback_list_version+StringUtil.getMapValue(map, "sys_user_id"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserFeedBackServiceImpl -- addUserFeedBack方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 查询意见反馈列表
	 */
	@Override
	public R getUserFeedBackList(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			map.put("userIdKey", StringUtil.getMapValue(map, "sys_user_id"));
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> userFeedBackList = userInfoCacheService.getUserFeedBackList(map);
			respondMap.put("userFeedBackList", userFeedBackList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserFeedBackServiceImpl -- getUserFeedBackList方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);

		}
	}


	/**
	 * 查询意见反馈详情
	 */
	@Override
	public R getUserFeedBackDetail(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			map.put("feedbackIdKey", StringUtil.getMapValue(map, "feed_back_id"));
			Map<String, Object> userFeedBackDetail = userInfoCacheService.getUserFeedBackDetail(map);
			respondMap.put("userFeedBackDetail", userFeedBackDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("UserFeedBackServiceImpl -- getUserFeedBackDetail方法处理异常:" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	
}
