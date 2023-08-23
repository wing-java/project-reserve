package com.ruoyi.project.develop.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.benefit.domain.Benefit;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.statistics.domain.SummaryUserRegisterEvery;
import com.ruoyi.project.develop.user.domain.UserInfo;

/**
 * 用户信息管理
 * @author Administrator
 *
 */
public interface UserInfoService {

	
	/**
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserInfoList(Map<String, Object> params);
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserInfoList(Map<String, Object> params);
	/**
	 * 导出用户信息
	 * @param params
	 * @return
	 */
	List<UserInfo> selectUserInfoList(Map<String, Object> params);
	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @return
	 */
	UserInfo getUserInfoById(String id);


	/**
	 * 系统冻结用户
	 * @param params
	 * @return
	 */
	R batchSysFreezeUser(Map<String, Object> params);
	/**
	 * 修改保存用户信息
	 * @param params
	 * @return
	 */
	R editUserInfo(Map<String, Object> params);
	
	
	/**
	 * 查询父级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getParentUserInfoList(Map<String, Object> params);
	/**
	 * 统计父级成员数据信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryParentUserInfoList(Map<String, Object> params);
	/**
	 * 导出父级成员信息列表
	 * @param params
	 * @return
	 */
	List<UserInfo> selectParentUserInfoList(Map<String, Object> params);


	/**
	 * 查询子级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getChildrenUserList(Map<String, Object> params);
	/**
	 * 导出子级团队成员列表
	 * @param params
	 * @return
	 */
	List<UserInfo> selectChildrenUserInfoList(Map<String, Object> params);
	/**
	 * 统计子级成员数据信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryChildrenUserInfoList(Map<String, Object> params);
	
	
	/**
	 * 查询用户流水详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBenefitRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出余额流水记录
	 * @param params
	 * @return
	 */
	List<Benefit> selectBenefitRecordBalanceList(Map<String, Object> params);


	/**
	 * 实时统计用户注册信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserRegisterList(Map<String, Object> params);
	/**
	 * 导出用户实时注册信息
	 * @param params
	 * @return
	 */
	List<SummaryUserRegisterEvery> selectSummaryUserRegisterList(Map<String, Object> params);
	/**
	 * 实时图表统计分析用户注册量
	 * @return
	 */
	List<Map<String, Object>> getUserRegisterList();
	/**
	 * 平台钱包信息统计
	 * @return
	 */
	Map<String, Object> getPlatformPurseInfo();
	
	/**
	 * 平移用户
	 * @param params
	 * @return
	 */
	R transUser(Map<String, Object> params);
	
	/**
	 * 新增业绩
	 * @param params
	 * @return
	 */
	R addPerformance(Map<String, Object> params);
	
}
	
	
