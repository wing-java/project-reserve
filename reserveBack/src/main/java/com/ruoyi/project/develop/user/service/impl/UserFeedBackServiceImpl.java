package com.ruoyi.project.develop.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.RedisNameVersionConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.user.domain.UserFeedBack;
import com.ruoyi.project.develop.user.mapper.UserFeedBackMapper;
import com.ruoyi.project.develop.user.service.UserFeedBackService;


/**
 * 用户意见反馈管理
 * @author Administrator
 *
 */
@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {
	
	@Autowired
	private UserFeedBackMapper userFeedBackMapper;

	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 查询用户意见反馈列表
	 */
	@Override
	public List<Map<String, Object>> getUserFeedBackList(Map<String, Object> params) {
		return userFeedBackMapper.getUserFeedBackList(params);
	}

	
	/**
	 * 导出用户意见反馈列表
	 */
	@Override
	public List<UserFeedBack> selectUserFeedBackList(Map<String, Object> params) {
		return userFeedBackMapper.selectUserFeedBackList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getUserFeedBackById(String id) {
		return userFeedBackMapper.getUserFeedBackById(id);
	}


	/**
	 * 修改系统回复
	 */
	@Override
	public R editUserFeedBack(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = userFeedBackMapper.updateUserFeedBack(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			
			//查询详情，清除缓存
			Map<String, Object> feedBackInfo = userFeedBackMapper.getUserFeedBackById(StringUtil.getMapValue(params, "feed_back_id"));
			redisUtils.remove(RedisNameConstants.user_feedback_id+StringUtil.getMapValue(params, "feed_back_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.user_feedback_list_version+StringUtil.getMapValue(feedBackInfo, "user_id"));
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}
	
}
