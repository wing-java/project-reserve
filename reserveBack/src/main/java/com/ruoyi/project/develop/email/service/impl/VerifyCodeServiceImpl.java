package com.ruoyi.project.develop.email.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.email.domain.VerifyCode;
import com.ruoyi.project.develop.email.mapper.VerifyCodeMapper;
import com.ruoyi.project.develop.email.service.VerifyCodeService;

/**
 * 验证码发送  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService 
{
	
	@Autowired
	private VerifyCodeMapper verifyCodeMapper;

	
	/**
	 * 查询验证码发送列表
	 */
	@Override
	public List<Map<String, Object>> getVerifyCodeList(Map<String, Object> params) {
		return verifyCodeMapper.getVerifyCodeList(params);
	}

	
	/**
	 * 导出验证码发送列表
	 */
	@Override
	public List<VerifyCode> selectVerifyCodeList(Map<String, Object> params) {
		return verifyCodeMapper.selectVerifyCodeList(params);
	}

}
