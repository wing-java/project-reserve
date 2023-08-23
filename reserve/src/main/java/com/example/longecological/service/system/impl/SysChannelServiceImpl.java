package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysChannelMapper;
import com.example.longecological.service.system.SysChannelService;

@Service
public class SysChannelServiceImpl implements SysChannelService {
	
	@Autowired
	SysChannelMapper sysChannelMapper;

	@Override
	public R getSysChannelList(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> sysChannelList = sysChannelMapper.getSysChannelList();
			respondMap.put("sysChannelList", sysChannelList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
