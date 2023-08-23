package com.ruoyi.project.develop.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.user.domain.UserReal;
import com.ruoyi.project.develop.user.mapper.UserRealMapper;
import com.ruoyi.project.develop.user.service.UserRealService;

@Service
public class UserRealServiceImpl implements UserRealService {

	@Autowired
	private UserRealMapper userRealMapper;
	
	@Override
	public List<Map<String, Object>> getUserRealList(Map<String, Object> params) {
		return userRealMapper.getUserRealList(params);
	}

	@Override
	public List<UserReal> selectUserRealList(Map<String, Object> params) {
		return userRealMapper.selectUserRealList(params);
	}

	@Override
	public Map<String, Object> getUserRealById(String id) {
		return userRealMapper.getUserRealById(id);
	}

	@Override
	@Transactional
	public R check(Map<String, Object> params) {
		try {
			//校验参数信息
			int num = 0;
			//查询审核记录
			Map<String, Object> record = userRealMapper.getUserRealById(StringUtil.getMapValue(params, "id"));
			//更新审核信息
			String date = TimeUtil.getDayString();
			String time = TimeUtil.getTimeString();
			Map<String, Object> data = new HashMap<>();
			data.put("id", StringUtil.getMapValue(params, "id"));
			data.put("status", StringUtil.getMapValue(params, "status"));
			data.put("up_date", date);
			data.put("up_time", time);
			data.put("note", StringUtil.getMapValue(params, "note"));
			data.put("update_by", ShiroUtils.getSysUser().getLoginName());
			num = userRealMapper.checkUserAgentInfo(data);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error("实名信息更新失败");
			}
			//更新用户表审核状态
			Map<String, Object> edit_user = new HashMap<>();
			edit_user.put("user_id", StringUtil.getMapValue(record, "user_id"));
			edit_user.put("auth_status", StringUtil.getMapValue(params, "status"));
			if("09".equals(StringUtil.getMapValue(params, "status"))) {
				//更新实名信息
				edit_user.put("nick_name", StringUtil.getMapValue(record, "name"));
				edit_user.put("real_name", StringUtil.getMapValue(record, "name"));
			}
			edit_user.put("up_date", TimeUtil.getDayString());
			edit_user.put("up_time", TimeUtil.getTimeString());
			num = userRealMapper.updateUserAuthStatus(edit_user);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error("实名状态更新失败");
			}
			return R.ok("操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error("操作异常");
		}
	}

}
