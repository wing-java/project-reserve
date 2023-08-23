package com.ruoyi.project.develop.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.statistics.domain.SummaryUserRegisterEvery;
import com.ruoyi.project.develop.user.domain.UserInfo;




/**
 * 用户信息管理
 * @author Administrator
 *
 */
public interface UserInfoMapper {


	/**
	 * 查询用户信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserInfoList(@Param("map") Map<String, Object> params);
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryUserInfoList(@Param("map") Map<String, Object> params);
	/**
	 * 导出用户列表
	 * @param params
	 * @return
	 */
	List<UserInfo> selectUserInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 查询父级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getParentUserInfoList(@Param("map") Map<String, Object> params);
	/**
	 * 导出父级成员信息列表
	 * @param params
	 * @return
	 */
	List<UserInfo> selectParentUserInfoList(@Param("map") Map<String, Object> params);
	/**
	 * 统计父级成员数据信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryParentUserInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 查询子级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getChildrenUserList(@Param("map") Map<String, Object> params);
	/**
	 * 导出子级成员信息列表
	 * @param params
	 * @return
	 */
	List<UserInfo> selectChildrenUserInfoList(@Param("map") Map<String, Object> params);
	/**
	 * 统计子级成员数据信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryChildrenUserInfoList(@Param("map") Map<String, Object> params);

	

	/**
	 * 更新用户状态
	 * @param userMap
	 * @return
	 */
	int updateUserStatus(@Param("map") Map<String, Object> userMap);
	/**
	 * 记录冻结账号
	 * @param userMap
	 * @return
	 */
	int addFrozeUser(@Param("map") Map<String, Object> userMap);
	/**
	 * 删除冻结账号
	 * @param userMap
	 * @return
	 */
	int deleteFrozeUser(@Param("map") Map<String, Object> userMap);

	
	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @return
	 */
	UserInfo getUserInfoById(@Param("user_id") String user_id);
	/**
	 * 根据用户ID查询并放入缓存
	 * @param user_id
	 * @return
	 */
	Map<String, Object> getRealUserInfoById(@Param("user_id") String user_id);
	/**
	 * 根据手机号码查询用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserByUserTel(@Param("map") Map<String, Object> params);
	/**
	 * 根据邮箱查询用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserByUserEmail(@Param("map") Map<String, Object> params);
	/**
	 * 根据id查询用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserMapById(@Param("user_id") String user_id);
	/**
	 * 根据用户账号查询用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserMapBySysUserAccount(@Param("sys_user_account") String sys_user_account);
	/**
	 * 查询该手机号注册人数
	 * @param string
	 * @return
	 */
	Integer getRegisterNumByUserTel(@Param("user_tel") String user_tel);
	/**
	 * 查询该邮箱注册人数
	 * @param string
	 * @return
	 */
	Integer getRegisterNumByUserEmail(@Param("user_email") String user_email);
	
	
	/**
	 * 更新用户信息
	 * @param params
	 * @return
	 */
	int updateUserInfo(@Param("map") Map<String, Object> params);
	
	
	
	/**
	 * 更新用户钱包信息
	 * @param map
	 * @return
	 */
	int updateUserWallet(@Param("map") Map<String, Object> map);
	
	/**
	 * 实时统计用户注册信息
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserRegisterList(@Param("map") Map<String, Object> params);
	/**
	 * 导出实时统计信息
	 * @param params
	 * @return
	 */
	List<SummaryUserRegisterEvery> selectSummaryUserRegisterList(@Param("map") Map<String, Object> params);
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
	 * 查询用户列表
	 * @return
	 */
	List<Map<String, Object>> getUserInfoListByParam();
	
	/**
	 * 查询推广码是否被使用
	 * @param params
	 * @return
	 */
	int getRegisterNumByUid(@Param("map") Map<String, Object> params);
	
	/**
	 * 更新直推和伞下人数
	 * @param map
	 * @return
	 */
	int updateUserReferUnderNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新推荐信息
	 * @param map
	 * @return
	 */
	int updateUserReferInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询当前用户伞下成员
	 * @param user_id
	 * @return
	 */
	List<Map<String, Object>> getUnderUserList(@Param("user_id") String user_id);
	
	/**
	 * 查询父级链成员
	 * @param parent_chain
	 * @return
	 */
	List<Map<String, Object>> getParentChainUserList(@Param("parent_chain") String parent_chain);
	
	/**
	 * 更新用户业绩
	 * @param map
	 * @return
	 */
	int updateUserPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新父级业绩
	 * @param map
	 * @return
	 */
	int updateUserPerformanceParent(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增用户业绩
	 * @param map
	 * @return
	 */
	int addUserSysPerformance(@Param("map") Map<String, Object> map);
	
}
