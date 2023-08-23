package com.ruoyi.project.develop.email.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.email.domain.VerifyCode;

/**
 * 验证码发送  数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface VerifyCodeMapper 
{

	/**
	 * 查询验证码发送列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getVerifyCodeList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出验证码发送列表
	 * @param params
	 * @return
	 */
	List<VerifyCode> selectVerifyCodeList(@Param("map") Map<String, Object> params);

}