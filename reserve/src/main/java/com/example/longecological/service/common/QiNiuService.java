package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;


public interface QiNiuService {

	
	/**
	 * 获取七牛云token值=====》供网页端（和APP端）本地使用（区别在于web路径加密）
	 * @param map
	 * @return
	 */
	R getQiNiuToken(Map<String, Object> map);

	
	/**
	 * 后台获取app七牛token=======》供管理后台调用
	 * @param map
	 * @return
	 */
	R getAppQiNiuToken(Map<String, Object> map);


	/**
	 * 生成七牛云Token、域名、文件路径，并且校验文件后缀名=======》供网页端（和APP端）本地使用（区别在于web路径加密）
	 * @param map
	 * @return
	 */
	R getQiNiuTokenAndUrl(Map<String, Object> map);
}
