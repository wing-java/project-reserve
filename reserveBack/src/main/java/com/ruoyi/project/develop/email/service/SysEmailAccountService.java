package com.ruoyi.project.develop.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.email.domain.SysEmailAccount;

/**
 * 邮箱账号 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface SysEmailAccountService 
{

	/**
	 * 查询邮箱账号列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysEmailAccountList(Map<String, Object> params);

	
	/**
	 * 导出邮箱账号列表
	 * @param params
	 * @return
	 */
	List<SysEmailAccount> selectSysEmailAccountList(Map<String, Object> params);

	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysEmailAccountById(String id);

	
	/**
	 * 编辑邮箱账号
	 * @param params
	 * @return
	 */
	R editSysEmailAccount(Map<String, Object> params);

	
	/**
	 * 新增邮箱账号
	 * @param params
	 * @return
	 */
	R addSysEmailAccount(Map<String, Object> params);


	/**
	 * 批量删除邮箱账号
	 * @param ids
	 * @return
	 */
	R batchRemoveSysEmailAccount(String ids);
	
}
