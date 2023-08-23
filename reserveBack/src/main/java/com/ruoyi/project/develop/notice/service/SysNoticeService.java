package com.ruoyi.project.develop.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;


/**
 * 公告 服务层
 * @author Administrator
 *
 */
public interface SysNoticeService
{
   
    
    /**
     * 查询公告列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getSysNoticeList(Map<String, Object> params);
    
    
    /**
     * 根据公告id查询公告详情
     * @param id
     * @return
     */
    public Map<String, Object> getNoticeById(String id);


    /**
     * 添加系统公告
     * @param params
     * @return
     */
	public R addSysNotice(Map<String, Object> params);


	/**
	 * 修改保存公告
	 * @param params
	 * @return
	 */
	public R editSysNotice(Map<String, Object> params);


	/**
	 * 批量删除系统公告
	 * @param ids
	 * @return
	 */
	public R batchRemoveSysNotice(String ids);
}
