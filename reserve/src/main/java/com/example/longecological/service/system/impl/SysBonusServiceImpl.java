package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysBonusMapper;
import com.example.longecological.service.system.SysBonusService;

@Service
public class SysBonusServiceImpl implements SysBonusService {
	
	@Autowired
	SysBonusMapper sysBonusMapper;

	@Override
	public R getSysBonusList(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> sysBonusList = sysBonusMapper.getSysBonusList(map);
			respondMap.put("sysBonusList", sysBonusList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	@Override
	public R getSysBonusDetail(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> sysBonusDetail = sysBonusMapper.getSysBonusDetail(map);
			respondMap.put("sysBonusDetail", sysBonusDetail);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
