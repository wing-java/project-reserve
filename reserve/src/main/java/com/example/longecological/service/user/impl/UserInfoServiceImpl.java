package com.example.longecological.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.SysFunctionLockParamService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.service.system.SysInfoCacheService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.service.user.UserInfoService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户信息相关
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	SmsCodeService smsCodeService;
	@Autowired
	VerifyTokenService verifyTokenService;
	@Autowired
	VerifyRecordService verifyRecordService;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	SysInfoCacheService sysInfoCacheService;
	
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	SysFunctionLockParamService sysFunctionLockParamService;
	
	
	/**
	 * 修改用户交易密码
	 */
	@Override
	@Transactional
	public R modifyPayPass(Map<String, Object> dataMap) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（2）校验短信验证码
			/*
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyPayPass);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			*/
			//（2）校验旧密码
			Map<String, Object> userMap = userInfoMapper.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			String old_pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("old_pay_password").toString().toUpperCase(), SysParamConstant.passNum).toString();//密码
			if(!old_pay_password.equals(userMap.get("pay_password").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
			}
			//（3）重置交易密码
//			dataMap.put("pay_password", new Md5Hash(dataMap.get("cre_date").toString()+dataMap.get("cre_time").toString(), dataMap.get("pay_password").toString().toUpperCase(), SysParamConstant.passNum).toString());//密码
			dataMap.put("pay_password", new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString().toUpperCase(), SysParamConstant.passNum).toString());//密码
			int i = userInfoMapper.updateUserPayPass(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999783,UserInfoCodeConstant.USER_INFO_MSG_999783);
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyPayPassOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}

	
	/**
	 * 修改登录密码
	 */
	@Override
	@Transactional
	public R modifyLoginPass(Map<String, Object> dataMap) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（2）校验短信验证码
			/*
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyLoginPass);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			*/
			//（2）校验旧密码
			Map<String, Object> userMap = userInfoMapper.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			String old_login_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("old_login_password").toString().toUpperCase(), SysParamConstant.passNum).toString();//密码
			if(!old_login_password.equals(userMap.get("login_password").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
			}
			//（3）重置交易密码
//			dataMap.put("login_password", new Md5Hash(dataMap.get("cre_date").toString()+dataMap.get("cre_time").toString(), dataMap.get("login_password").toString().toUpperCase(), SysParamConstant.passNum).toString());//密码
			dataMap.put("login_password", new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("login_password").toString().toUpperCase(), SysParamConstant.passNum).toString());//密码
			int i = userInfoMapper.updateUserLoginPass(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999784,UserInfoCodeConstant.USER_INFO_MSG_999784);
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyLoginPassOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 修改用户资料（头像）
	 */
	@Override
	public R modifyUserInfo(Map<String, Object> dataMap) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（1）如果是更新用户名
			/*if(!StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_account"))) {
				//（1）校验用户名格式
				if(StringUtil.getMapValue(dataMap, "sys_user_account").length()>11) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999774,UserInfoCodeConstant.USER_INFO_MSG_999774);
				}
				//（2）校验用户名是否已经存在
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
				if(userMap!=null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999793, UserInfoCodeConstant.USER_INFO_MSG_999793);
				}
			}*/
			//（2）如果是更新用户名
			if(!StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "nick_name"))) {
				//（1）校验用户名格式
				if(StringUtil.getMapValue(dataMap, "nick_name").length()>11) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999774,UserInfoCodeConstant.USER_INFO_MSG_999774);
				}
				//（2）校验该昵称是否已经存在
				/*Map<String, Object> userMap = userInfoMapper.getUserInfoByNickName(dataMap.get("nick_name").toString());
				if(userMap!=null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999793, UserInfoCodeConstant.USER_INFO_MSG_999793);
				}*/
			}
			//（3）更新用户资料
			dataMap.put("up_date", TimeUtil.getDayString());//更新日期
			dataMap.put("up_time", TimeUtil.getTimeString());//更新时间
			int i = userInfoMapper.updateUserInfo(dataMap);
			if(i != 1) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999797, UserInfoCodeConstant.USER_INFO_MSG_999797);
			}
			//（3）删除缓存
			redisUtils.remove(RedisNameConstants.user_info_id+dataMap.get("sys_user_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}



	/**
	 * 修改手机号第一步：校验旧手机号
	 */
	@Override
	@Transactional
	public R modifyTelFirst(Map<String, Object> dataMap) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（1）校验短信验证码
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyTelFirst);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（2）返回校验通过的唯一标识
			String valid_flag = StringUtil.getDateTimeAndRandomForID();//随机数ID（字母数字混合）
			String redis_valid_flag_key = RedisNameConstants.modify_tel_valid_flag+dataMap.get("sys_user_id").toString();//修改手机号校验通过的唯一标识
			redisUtils.set(redis_valid_flag_key, valid_flag, SysParamConstant.verification_code_seconds);//存入redis
			
			 //（3）返回验证通过的信息
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("valid_flag", valid_flag);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyTelFirstOper方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}




	/**
	 * 修改手机号第二步：设置新的手机号
	 */
	@Override
	@Transactional
	public R modifyTelSecond(Map<String, Object> dataMap) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			int i=0;
			//（1）新的手机号信息校验
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			if(userMap!=null) {
				if(dataMap.get("sys_user_id").toString().equals(userMap.get("id").toString())) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999869,MsgImgCodeConstant.MESSAGE_MSG_999869);
				}else {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999870,MsgImgCodeConstant.MESSAGE_MSG_999870);
				}
			}
			//（2）校验短信验证码
			R checkSmsResult = verifyRecordService.compare(dataMap.get("sys_user_id").toString(), null, VerifyConstant.BusType_FrontModifyTelSecond, VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（3）返回的标识信息校验
			//从redis中拿出校验通过的标识
			String redis_valid_flag_key = RedisNameConstants.modify_tel_valid_flag+dataMap.get("sys_user_id").toString();
			Object valid_flag = redisUtils.get(redis_valid_flag_key);
			if(valid_flag == null){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999776,UserInfoCodeConstant.USER_INFO_MSG_999776);
			}
			if(!valid_flag.toString().toUpperCase().equals(StringUtil.getMapValue(dataMap, "valid_flag").toUpperCase())){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999775,UserInfoCodeConstant.USER_INFO_MSG_999775);
			}
			//（4）修改用户手机号
			i = userInfoMapper.updateUserTel(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999764, UserInfoCodeConstant.USER_INFO_MSG_999764);
			}
			//（5）删除缓存
			redisUtils.remove(redis_valid_flag_key);//校验通过，删除
			redisUtils.remove(RedisNameConstants.user_info_id+dataMap.get("sys_user_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyTelSecondOper方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
	/**
	 * 根据编号查询用户缓存信息
	 */
	@Override
	public R getUserInfoCacheById(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询结果返回处理
			Map<String, Object> userInfo = userInfoCacheService.getUserInfoCacheById(map.get("sys_user_id").toString());
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	/**
	 * 实时查询用户信息
	 */
	@Override
	public R getRealUserInfo(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（1）查询用户信息
			Map<String, Object> userInfo = userInfoMapper.getRealUserInfoById(map.get("sys_user_id").toString());
			if(!StringUtil.isEmpty(StringUtil.getMapValue(userInfo, "pay_password"))) {
				userInfo.put("pay_password", "99999999999999999999999999");
			}
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	/**
	 * 实时查询用户钱包信息
	 */
	@Override
	public R getUserWalletInfo(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//（1）查询用户信息
			Map<String, Object> userInfo = userInfoMapper.getUserWalletInfo(map.get("sys_user_id").toString());
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询用户团队列表
	 */
	@Override
	public R getUserTeamInfo(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
//			Map<String, Object> userInfo = userInfoMapper.getRealUserInfoById(map.get("sys_user_id").toString());
			Map<String, Object> userInfo = userInfoMapper.getUserTeamInfo(map.get("sys_user_id").toString());
			//String direct_num = userInfoMapper.getUnderTeamNumByAlgebra(userInfo.get("id").toString(), userInfo.get("algebra").toString(), "2");
			//查询结果返回处理
			//userInfo.put("direct_num", direct_num);
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	/**
	 * 查询用户团队列表
	 */
	@Override
	public R getUserTeamList(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			Map<String, Object> userInfo = userInfoMapper.getRealUserInfoById(map.get("sys_user_id").toString());
			List<Map<String, Object>> userTeamList = null;
			//直推
			if(TypeStatusConstant.user_team_team_type_01.equals(StringUtil.getMapValue(map, "team_type"))) {
				userTeamList = userInfoMapper.getUserReferList(map);
			//间推
			}else if(TypeStatusConstant.user_team_team_type_02.equals(StringUtil.getMapValue(map, "team_type"))) {
				map.put("user_id", userInfo.get("id").toString());
				map.put("user_algebra", userInfo.get("algebra").toString());
				map.put("algebra_num", "2");
				userTeamList = userInfoMapper.getUnderTeamListByAlgebra(map);
			//三级
			}else if(TypeStatusConstant.user_team_team_type_03.equals(StringUtil.getMapValue(map, "team_type"))) {
				map.put("user_id", userInfo.get("id").toString());
				map.put("user_algebra", userInfo.get("algebra").toString());
				map.put("algebra_num", "3");
				userTeamList = userInfoMapper.getUnderTeamListByAlgebra(map);
			//团队
			}else if(TypeStatusConstant.user_team_team_type_04.equals(StringUtil.getMapValue(map, "team_type"))) {
				userTeamList = userInfoMapper.getUserTeamList(map);
			}else {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userTeamList", userTeamList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	/**
	 * 校验用户信息是否存在
	 */
	@Override
	public R checkUserExist(Map<String, Object> map) {
		try {
			if(StringUtils.isNotEmpty(StringUtil.getMapValue(map, "user_account"))) {
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(map.get("user_account").toString());
				if(userMap!=null) {
					return R.error(CommonCodeConstant.COMMON_CODE_999960, CommonCodeConstant.COMMON_MSG_999960);
				}
				return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983);
			}else {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 根据手机号查询用户信息
	 */
	@Override
	public R getUserByTel(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询该用户信息
			Map<String, Object> userInfo = userInfoMapper.getUserByTel(StringUtil.getMapValue(map, "user_tel"));
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userInfo", userInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 实名认证
	 */
	@Override
	@Transactional
	public R submitUserReal(Map<String, Object> map) {
		int num = 0;
		try {
			//校验参数
			R checkUserReal = this.checkUserReal(map);
			if(!Boolean.valueOf(checkUserReal.get(R.SUCCESS_TAG).toString())) {
				return checkUserReal;
			}
			//进行第三方验证
			
			//查询认证记录
			Map<String, Object> real = userInfoMapper.getUserReal(StringUtil.getMapValue(map, "sys_user_id"));
			if(real == null) {
				//添加实名记录
				Map<String, Object> edit_real = new HashMap<>();
				edit_real.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
				edit_real.put("name", StringUtil.getMapValue(map, "name"));
				edit_real.put("id_card", StringUtil.getMapValue(map, "id_card"));
				edit_real.put("card_photo", StringUtil.getMapValue(map, "card_photo"));
				edit_real.put("status", "00");
				edit_real.put("cre_date", TimeUtil.getDayString());
				edit_real.put("cre_time", TimeUtil.getTimeString());
				num = userInfoMapper.addUserReal(edit_real);
				if(num != 1) {
					return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
				}
			}else {
				//添加实名记录
				Map<String, Object> edit_real = new HashMap<>();
				edit_real.put("user_id", StringUtil.getMapValue(map, "sys_user_id"));
				edit_real.put("name", StringUtil.getMapValue(map, "name"));
				edit_real.put("id_card", StringUtil.getMapValue(map, "id_card"));
				edit_real.put("card_photo", StringUtil.getMapValue(map, "card_photo"));
				edit_real.put("status", "00");
				edit_real.put("note", null);
				edit_real.put("up_date", TimeUtil.getDayString());
				edit_real.put("up_time", TimeUtil.getTimeString());
				num = userInfoMapper.updateUserReal(edit_real);
				if(num != 1) {
					return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
				}
			}
			//更新实名状态
			Map<String, Object> edit_user = new HashMap<>();
			edit_user.put("id", StringUtil.getMapValue(map, "sys_user_id"));
			edit_user.put("auth_status", "02");
			num = userInfoMapper.updateUserAuthStatus(edit_user);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- submitUserReal方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	public R checkUserReal(Map<String, Object> map) {
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
			return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
		}
		//校验姓名
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "name"))) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999736,UserInfoCodeConstant.USER_INFO_MSG_999736);
		}
		//校验身份证号\证件照信息校验
		if(!RegexUtil.isValidIdCardFirst(map.get("id_card").toString())) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999737,UserInfoCodeConstant.USER_INFO_MSG_999737);
		}
		//校验是否已提交
		int count = userInfoMapper.getUserRealByIdCard(map);
		if(count > 0) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999732,UserInfoCodeConstant.USER_INFO_MSG_999732);
		}
		//证件照
//		if(map.get("card_photo").toString().split(",").length!=2) {
//			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999735,UserInfoCodeConstant.USER_INFO_MSG_999735);
//		}
		//银行卡号
		/*
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "account"))) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999734,UserInfoCodeConstant.USER_INFO_MSG_999734);
		}
		*/
		//银行名称
		/*
		if(StringUtil.isEmpty(StringUtil.getMapValue(map, "bank_name"))) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999733,UserInfoCodeConstant.USER_INFO_MSG_999733);
		}
		*/
		return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
	}


	@Override
	public R getUserReal(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询该用户信息
			Map<String, Object> userReal = userInfoMapper.getUserReal(StringUtil.getMapValue(map, "sys_user_id"));
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userReal", userReal);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询统计收益
	 */
	@Override
	public R getUserByTeamBenefit(Map<String, Object> map) {
		try {
			if(StringUtil.isEmpty(StringUtil.getMapValue(map, "sys_user_id"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999990, CommonCodeConstant.COMMON_MSG_999990);
			}
			//查询该用户信息
			Map<String, Object> userBenefit = userInfoMapper.getUserByTeamBenefit(StringUtil.getMapValue(map, "sys_user_id"));
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userBenefit", userBenefit);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}
