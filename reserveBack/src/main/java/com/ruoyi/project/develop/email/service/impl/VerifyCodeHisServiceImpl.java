package com.ruoyi.project.develop.email.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.email.domain.VerifyCode;
import com.ruoyi.project.develop.email.mapper.VerifyCodeHisMapper;
import com.ruoyi.project.develop.email.service.VerifyCodeHisService;

/**
 * 验证码发送  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class VerifyCodeHisServiceImpl implements VerifyCodeHisService 
{
	
	@Autowired
	private VerifyCodeHisMapper verifyCodeHisMapper;

	
	/**
	 * 查询验证码发送列表
	 */
	@Override
	public List<Map<String, Object>> getVerifyCodeHisList(Map<String, Object> params) {
		return verifyCodeHisMapper.getVerifyCodeHisList(params);
	}

	
	/**
	 * 导出验证码发送列表
	 */
	@Override
	public List<VerifyCode> selectVerifyCodeHisList(Map<String, Object> params) {
		return verifyCodeHisMapper.selectVerifyCodeHisList(params);
	}

}
