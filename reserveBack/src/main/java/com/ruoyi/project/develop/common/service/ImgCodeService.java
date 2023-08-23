package com.ruoyi.project.develop.common.service;

import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;

/**
 * 图形验证码
 * @author Administrator
 *
 */
public interface ImgCodeService {

	
	/**
	 * 生成图形验证码
	 * @return
	 */
	R createCode();

	
	/**
	 * 校验图形验证码======>redis中处理的
	 * @param map
	 * @return
	 */
	R checkImgCode(Map<String, Object> map);


	/**
	 * 校验图像验证码=======>shiro的session处理的
	 * @param map
	 * @return
	 */
	R checkImgCodeBack(Map<String, Object> map);
	
	
}
