package com.example.longecological.service.async.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.async.AsyncUserPerformanceService;

@Service
public class AsyncUserPerformanceServiceImpl implements AsyncUserPerformanceService {
	
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public void dealPersonPerformance(String user_id, BigDecimal performance, String date, String time) throws Exception{
		Map<String, Object> edit_performance = new HashMap<>();
		edit_performance.put("user_id", user_id);
		edit_performance.put("performance", performance);
		edit_performance.put("date", date);
		edit_performance.put("time", time);
		int num = userInfoMapper.updatePersonPerformance(edit_performance);
		if(num != 1) {
			throw new Exception("个人业绩更新异常");
		}
	}

	@Override
	public void dealTeamPerformance(String parent_chain, BigDecimal performance, String date, String time) throws Exception{
		Map<String, Object> edit_performance = new HashMap<>();
		edit_performance.put("parent_chain", parent_chain);
		edit_performance.put("performance", performance);
		edit_performance.put("date", date);
		edit_performance.put("time", time);
		int num = userInfoMapper.updateTeamPerformance(edit_performance);
		if(num < 1) {
			throw new Exception("上级业绩更新异常");
		}
		
	}

}
