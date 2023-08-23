package com.ruoyi.project.develop.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;

public interface SysBonusService {

	/**
     * 查询
     * @param params
     * @return
     */
    public List<Map<String, Object>> getSysBonusList(Map<String, Object> params);
    
    
    /**
     * 
     * @param id
     * @return
     */
    public Map<String, Object> getBonusById(String id);


    /**
     * 添加
     * @param params
     * @return
     */
	public R addSysBonus(Map<String, Object> params);


	/**
	 * 修改
	 * @param params
	 * @return
	 */
	public R editSysBonus(Map<String, Object> params);


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public R batchRemoveSysBonus(String ids);
}
