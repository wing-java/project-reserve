package com.example.longecological.service.system;

import java.util.List;
import java.util.Map;


/**
 * 系统公告缓存相关
 * @author Administrator
 *
 */
public interface SysInfoCacheService {


	/**
	 * 查询系统最新公告
	 * @param map
	 * @return
	 */
	Map<String, Object> getNewNotice(Map<String, Object> map);
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysNoticeList(Map<String, Object> map);
	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysNoticeDetail(Map<String, Object> map);
	
	
	/**
	 * 查询系统最新新闻
	 * @param map
	 * @return
	 */
	Map<String, Object> getNewNews(Map<String, Object> map);
	/**
	 * 查询系统新闻列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysNewsList(Map<String, Object> map);
	/**
	 * 查询系统新闻详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysNewsDetail(Map<String, Object> map);
	
	
	/**
	 * 查询app图片列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAppImgList(Map<String, Object> map);
	
	/**
	 * 查询系统最新版本
	 * @param map
	 * @return
	 */
	Map<String, Object> getNewVersion(Map<String, Object> map);
	
	/**
	 * 根据类型查询系统协议合同信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getSysContractByType(Map<String, Object> map);
	
}
