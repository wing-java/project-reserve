package com.ruoyi.project.develop.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;


/**
 * 新闻资讯 服务层
 * @author Administrator
 *
 */
public interface SysNewsService
{
   
    
    /**
     * 查询新闻资讯列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getSysNewsList(Map<String, Object> params);
    
    
    /**
     * 根据id查询新闻资讯详情
     * @param id
     * @return
     */
    public Map<String, Object> getNewsById(String id);


    /**
     * 添加系统新闻资讯
     * @param params
     * @return
     */
	public R addSysNews(Map<String, Object> params);


	/**
	 * 修改保存新闻资讯
	 * @param params
	 * @return
	 */
	public R editSysNews(Map<String, Object> params);


	/**
	 * 批量删除系统新闻资讯
	 * @param ids
	 * @return
	 */
	public R batchRemoveSysNews(String ids);
}
