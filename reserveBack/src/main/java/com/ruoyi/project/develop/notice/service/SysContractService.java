package com.ruoyi.project.develop.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;


/**
 * 合同 服务层
 * @author Administrator
 *
 */
public interface SysContractService
{
   
    
    /**
     * 查询合同列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getSysContractList(Map<String, Object> params);
    
    
    /**
     * 根据id查询合同详情
     * @param id
     * @return
     */
    public Map<String, Object> getContractById(String id);


    /**
     * 添加系统合同
     * @param params
     * @return
     */
	public R addSysContract(Map<String, Object> params);


	/**
	 * 修改保存合同
	 * @param params
	 * @return
	 */
	public R editSysContract(Map<String, Object> params);


	/**
	 * 批量删除系统合同
	 * @param ids
	 * @return
	 */
	public R batchRemoveSysContract(String ids);
}
