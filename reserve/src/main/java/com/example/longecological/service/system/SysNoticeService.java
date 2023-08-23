package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统公告相关
 * @author Administrator
 *
 */
public interface SysNoticeService {

	/**
	 * 查询系统最新公告
	 * @param map
	 * @return
	 */
	R getNewNotice(Map<String, Object> map);

	
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	R getSysNoticeList(Map<String, Object> map);


	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	R getSysNoticeDetail(Map<String, Object> map);

}
