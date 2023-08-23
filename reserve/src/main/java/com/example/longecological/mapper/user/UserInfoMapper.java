package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户管理
 * @author Administrator
 *
 */
public interface UserInfoMapper {

	/**
	 * 根据id查询账户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserInfoById(@Param("user_id") String user_id);
	/**
	 * 根据user_account查询账户信息
	 * @param user_account
	 * @return
	 */
	Map<String, Object> getUserInfoByUserAccount(@Param("sys_user_account") String sys_user_account);
	/**
	 * 根据nick_name查询账户信息
	 * @param nick_name
	 * @return
	 */
	Map<String, Object> getUserInfoByNickName(@Param("nick_name") String nick_name);
	/**
	 * 根据UID查询用户账户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserInfoByUid(@Param("uid") String uid);
	/**
	 * 根据用户账号和（手机号或者邮箱）查询用户信息
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getUserInfoByUserAccountTel(@Param("map") Map<String, Object> dataMap);
	/**
	 * 通过用户ID查询用户基础信息放入缓存
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserInfoCacheById(@Param("user_id") String user_id);
	/**
	 * 根据编号查询实时用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getRealUserInfoById(@Param("user_id") String user_id);
	/**
	 * 实时查询用户钱包信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getUserWalletInfo(@Param("user_id") String user_id);

	
	/**
	 * 更新用户设备信息
	 * @param map
	 * @return
	 */
	int updateUserDeviceInfo(@Param("map") Map<String, Object> map);
	/**
	 * 更新用户版本信息
	 * @param map
	 * @return
	 */
	int updateUserVersionInfo(@Param("map") Map<String, Object> map);
	/**
	 * 记录用户登录日志
	 * @param map
	 * @return
	 */
	int addUserLoginInfo(@Param("map") Map<String, Object> map);
	/**
	 * 记录用户异常日志
	 * @param map
	 * @return
	 */
	int addUserErrorOperLog(@Param("map") Map<String, Object> map);
	/**
	 * 更新所有父级伞下人数
	 * @param dataMap
	 * @return
	 */
	int updateParentUnderNum(@Param("map") Map<String, Object> dataMap);
	/**
	 * 更新父级直推人数
	 * @param dataMap
	 * @return
	 */
	int updateUserReferNum(@Param("map") Map<String, Object> dataMap);
	/**
	 * 保存用户信息
	 * @param dataMap
	 * @return
	 */
	int saveUserInfo(@Param("map") Map<String, Object> dataMap);
	/**
	 * 更新用户资料（头像）
	 * @param dataMap
	 * @return
	 */
	int updateUserInfo(@Param("map") Map<String, Object> dataMap);
	
	
	/**
	 * 更新用户登录密码
	 * @param dataMap
	 * @return
	 */
	int updateUserLoginPass(@Param("map") Map<String, Object> dataMap);
	/**
	 * 更新用户交易密码
	 * @param dataMap
	 * @return
	 */
	int updateUserPayPass(@Param("map") Map<String, Object> dataMap);
	/**
	 * 更新用户登录密码并锁定用户绑定注册类型
	 * @param dataMap
	 * @return
	 */
	int updateUserLoginPassRegisterType(@Param("map") Map<String, Object> dataMap);
	/**
	 * 修改用户手机号
	 * @param map
	 * @return
	 */
	int updateUserTel(@Param("map") Map<String, Object> map);
	

	/**
	 * 查询冻结用户信息
	 * @param user_id
	 * @return
	 */
	Map<String, Object> getUserFreezeCacheById(@Param("user_id") String user_id);
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
	 * 查询伞下指定代数的用户数量
	 * @param user_algebra
	 * @param algebra_num
	 * @param string 
	 * @return
	 */
	String getUnderTeamNumByAlgebra(@Param("user_id") String user_id, @Param("user_algebra") String user_algebra, @Param("algebra_num") String algebra_num);
	/**
	 * 查询伞下指定代数的用户列表
	 * @param user_id
	 * @param user_algebra
	 * @param algebra_num
	 * @return
	 */
	List<Map<String, Object>> getUnderTeamListByAlgebra(@Param("map") Map<String, Object> map);
	/**
	 * 查询用户直推列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserReferList(@Param("map") Map<String, Object> map);
	/**
	 * 查询用户间推列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserIndirectList(@Param("map") Map<String, Object> map);
	/**
	 * 查询用户团队列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserTeamList(@Param("map") Map<String, Object> map);
	/**
	 * 查询用户上下级信息
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getUserSupervisorInfo(@Param("map") Map<String, Object> dataMap);
	/**
	 * 更新用户余额
	 * @param map
	 * @return
	 */
	int updateUserBalanceNum(@Param("map") Map<String, Object> map);
	/**
	 * 根据手机号查询用户信息
	 * @param user_tel
	 * @return
	 */
	Map<String, Object> getUserByTel(@Param("user_tel") String user_tel);
	
	/**
	 * 更新用户文票积分
	 * @param map
	 * @return
	 */
	int updateUserTicketNum(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询团队头部信息
	 * @param user_id
	 * @return
	 */
	Map<String, Object> getUserTeamInfo(@Param("user_id") String user_id);
	
	/**
	 * 添加钱包
	 * @param user_id
	 * @return
	 */
	int addUserWallet(@Param("user_id") String user_id);
	
	/**
	 * 添加总流水
	 * @param user_id
	 * @return
	 */
	int addSummaryUserBenefit(@Param("user_id") String user_id);
	
	/**
	 * 更新个人业绩
	 * @param map
	 * @return
	 */
	int updatePersonPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新上级业绩
	 * @param map
	 * @return
	 */
	int updateTeamPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询实名信息
	 * @param user_id
	 * @return
	 */
	Map<String, Object> getUserReal(@Param("user_id") String user_id);
	
	/**
	 * 添加实名
	 * @param map
	 * @return
	 */
	int addUserReal(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新实名
	 * @param map
	 * @return
	 */
	int updateUserReal(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新实名状态
	 * @param map
	 * @return
	 */
	int updateUserAuthStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询会员推荐奖励
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserByTeamBenefit(@Param("user_id") String user_id);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	int getUserRealByIdCard(@Param("map") Map<String, Object> map);
	
}
