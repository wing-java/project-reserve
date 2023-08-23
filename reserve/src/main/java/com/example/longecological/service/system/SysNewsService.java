package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统新闻相关
 * @author Administrator
 *
 */
public interface SysNewsService {

	/**
	 * 查询系统最新新闻
	 * @param map
	 * @return
	 */
	R getNewNews(Map<String, Object> map);

	
	/**
	 * 查询系统新闻列表
	 * @param map
	 * @return
	 */
	R getSysNewsList(Map<String, Object> map);


	/**
	 * 查询系统新闻详情
	 * @param map
	 * @return
	 */
	R getSysNewsDetail(Map<String, Object> map);

}
