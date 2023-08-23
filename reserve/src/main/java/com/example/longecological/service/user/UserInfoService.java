package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户信息相关
 * @author Administrator
 *
 */
public interface UserInfoService {

	
	/**
	 * 修改支付密码
	 * @param map
	 * @return
	 */
	R modifyPayPass(Map<String, Object> map);

	
	/**
	 * 修改登录密码
	 * @param map
	 * @return
	 */
	R modifyLoginPass(Map<String, Object> map);


	/**
	 * 修改用户资料
	 * @param map
	 * @return
	 */
	R modifyUserInfo(Map<String, Object> map);


	/**
	 * 修改手机号第一步：校验旧手机号
	 * @param map
	 * @return
	 */
	R modifyTelFirst(Map<String, Object> map);


	/**
	 * 修改手机号第一步：设置新的手机号
	 * @param map
	 * @return
	 */
	R modifyTelSecond(Map<String, Object> map);
	
	
	/**
	 * 根据编号查询用户缓存信息
	 * @param map
	 * @return
	 */
	R getUserInfoCacheById(Map<String, Object> map);
	/**
	 * 实时查询用户信息
	 * @param map
	 * @return
	 */
	R getRealUserInfo(Map<String, Object> map);
	/**
	 * 实时查询钱包信息
	 * @param map
	 * @return
	 */
	R getUserWalletInfo(Map<String, Object> map);

	/**
	 * 查询用户团队信息
	 * @param map
	 * @return
	 */
	R getUserTeamInfo(Map<String, Object> map);
	/**
	 * 查询用户团队列表
	 * @param map
	 * @return
	 */
	R getUserTeamList(Map<String, Object> map);
	/**
	 * 校验用户信息是否存在
	 * @param map
	 * @return
	 */
	R checkUserExist(Map<String, Object> map);
	/**
	 * 根据手机号查询用户信息
	 * @param map
	 * @return
	 */
	R getUserByTel(Map<String, Object> map);
	/**
	 * 实名认证
	 * @param map
	 * @return
	 */
	R submitUserReal(Map<String, Object> map);
	/**
	 * 查询实名
	 * @param map
	 * @return
	 */
	R getUserReal(Map<String, Object> map);
	/**
	 * 查询团队收益
	 * @param map
	 * @return
	 */
	R getUserByTeamBenefit(Map<String, Object> map);
	
}
