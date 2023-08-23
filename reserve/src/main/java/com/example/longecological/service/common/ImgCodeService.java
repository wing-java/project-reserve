package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 图形验证码
 * @author Administrator
 *
 */
public interface ImgCodeService {

	
	/**
	 * 生成图形验证码
	 * @param map 
	 * @return
	 */
	R createImgCode(Map<String, Object> map);

	
	/**
	 * 校验图形验证码======>redis中处理的
	 * @param map
	 * @return
	 */
	R checkImgCode(Map<String, Object> map);
	
}
