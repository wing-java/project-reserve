package com.ruoyi.project.develop.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.email.domain.VerifyCode;

/**
 * 验证码发送 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface VerifyCodeService 
{

	/**
	 * 查询验证码发列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getVerifyCodeList(Map<String, Object> params);

	
	/**
	 * 导出验证码发列表
	 * @param params
	 * @return
	 */
	List<VerifyCode> selectVerifyCodeList(Map<String, Object> params);
	
}
