package com.ruoyi.project.develop.common.service;

import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;


public interface QiNiuService {

	
	/**
	 * 获取七牛云token值
	 * @param map
	 * @return
	 */
	R getQiNiuToken(Map<String, Object> map);



	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名
	 * @param map
	 * @return
	 */
	R getQiNiuTokenAndUrl(Map<String, Object> map);
}
