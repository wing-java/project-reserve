package com.example.longecological.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.annotations.EnableCacheVersionService;
import com.example.longecological.annotations.EnableCacheVersionService.CacheVersionOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.mapper.system.SysAppImgMapper;
import com.example.longecological.mapper.system.SysContractMapper;
import com.example.longecological.mapper.system.SysNewsMapper;
import com.example.longecological.mapper.system.SysNoticeMapper;
import com.example.longecological.mapper.system.SysVersionMapper;
import com.example.longecological.service.system.SysInfoCacheService;


/**
 * 系统公告缓存相关
 * @author Administrator
 *
 */
@Service
public class SysInfoCacheServiceImpl implements SysInfoCacheService {
	
	@Autowired
	SysNoticeMapper sysNoticeMapper;
	@Autowired
	SysNewsMapper sysNewsMapper;
	@Autowired
	SysAppImgMapper sysAppImgMapper;
	@Autowired
	SysVersionMapper sysVersionMapper;
	@Autowired
	SysContractMapper sysContractMapper;
	
	
	/**
	 * 查询最新公告
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_notice_info_new,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getNewNotice(Map<String, Object> map) {
		return sysNoticeMapper.getNewNotice(map);
	}
	/**
	 * 查询公告列表
	 */
	@EnableCacheVersionService(keyPrefix=RedisNameConstants.sys_notice_info_list,
			fieldKey="#map['lastIdKey']", 
			versionKey=RedisNameVersionConstants.sys_notice_info_list_version,
			fieldVersionKey="",
			cacheVersionOperation = CacheVersionOperation.QUERY,
			expireTime=3600)
	@Override
	public List<Map<String, Object>> getSysNoticeList(Map<String, Object> map) {
		return sysNoticeMapper.getSysNoticeList(map);
	}
	/**
	 * 查询系统公告详情
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_notice_info_detail,
			fieldKey="#map['notice_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysNoticeDetail(Map<String, Object> map) {
		return sysNoticeMapper.getSysNoticeDetail(map);
	}
	
	
	/**
	 * 查询最新新闻
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_news_info_new,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getNewNews(Map<String, Object> map) {
		return sysNewsMapper.getNewNews(map);
	}
	/**
	 * 查询新闻列表
	 */
	@EnableCacheVersionService(keyPrefix=RedisNameConstants.sys_news_info_list,
			fieldKey="#map['lastIdKey']", 
			versionKey=RedisNameVersionConstants.sys_news_info_list_version,
			fieldVersionKey="",
			cacheVersionOperation = CacheVersionOperation.QUERY,
			expireTime=3600)
	@Override
	public List<Map<String, Object>> getSysNewsList(Map<String, Object> map) {
		return sysNewsMapper.getSysNewsList(map);
	}
	/**
	 * 查询系统新闻详情
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_news_info_detail,
			fieldKey="#map['news_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysNewsDetail(Map<String, Object> map) {
		return sysNewsMapper.getSysNewsDetail(map);
	}
	
	
	/**
	 * 查询app图片列表列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_app_img,
			fieldKey="#map['img_type']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getAppImgList(Map<String, Object> map) {
		return sysAppImgMapper.getAppImgList(map);
	}
	
	/**
	 * 查询最新版本
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_version_info_new,
			fieldKey="#map['device_type']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getNewVersion(Map<String, Object> map) {
		return sysVersionMapper.getNewVersion(map);
	}
	
	/**
	 * 根据类型查询系统协议合同信息
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.sys_contract_info_type,
			fieldKey="#map['contract_type']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSysContractByType(Map<String, Object> map) {
		return sysContractMapper.getSysContractByType(map);
	}
}
