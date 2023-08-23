package com.ruoyi.project.develop.email.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.email.domain.SysEmailAccount;

/**
 * 邮箱账号  数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface SysEmailAccountMapper 
{

	/**
	 * 查询邮箱账号列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysEmailAccountList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出邮箱账号列表
	 * @param params
	 * @return
	 */
	List<SysEmailAccount> selectSysEmailAccountList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysEmailAccountById(@Param("email_account_num") String emailAccount_id);


	/**
	 * 更新邮箱账号
	 * @param params
	 * @return
	 */
	int updateSysEmailAccount(@Param("map") Map<String, Object> params);


	/**
	 * 新增邮箱账号
	 * @param params
	 * @return
	 */
	int addSysEmailAccount(@Param("map") Map<String, Object> params);


	/**
	 * 根据邮箱账号编号删除
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteSysEmailAccount(@Param("email_account_num") String email_account_num);


	/**
	 * 查询大于当前编号的邮箱编号信息
	 * @param emailAccountNum
	 * @return
	 */
	List<Map<String, Object>> getLagerEmailAccountList(@Param("email_account_num") String emailAccountNum);


	/**
	 * 更新大于当前邮箱邮箱编号的邮箱编号（编号=编号-1）
	 * @param emailAccountNum
	 * @return
	 */
	int updateLagerEmailAccount(@Param("email_account_num") String emailAccountNum);


	/**
	 * 查询当前剩余邮箱个数
	 * @return
	 */
	Integer selectRemainEmailNum();
}