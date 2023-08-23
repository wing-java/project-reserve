package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysCompanyMapper;
import com.example.longecological.service.system.SysCompanyService;


/**
 * 系统地区相关
 * @author Administrator
 *
 */
@Service
public class SysCompanyServiceImpl implements SysCompanyService {
	
	@Autowired
	SysCompanyMapper sysCompanyMapper;
	

	/**
	 * 查询公司信息
	 */
	@Override
	public R getSysCompanyInfo(Map<String, Object> map) {
		try {
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> sysCompany = sysCompanyMapper.getSysCompanyInfo(map);
			respondMap.put("sysCompany", sysCompany);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
