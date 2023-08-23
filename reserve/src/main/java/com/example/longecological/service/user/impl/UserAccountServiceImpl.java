package com.example.longecological.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.OperParamConstant;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.RedisNameVersionConstants;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserAccountMapper;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.SysParamService;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.user.UserAccountService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户账户相关
 * @author Administrator
 *
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	
	
	@Autowired
	RedisUtils redisUtils;
	@Autowired
	SmsCodeService smsCodeService;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	UserAccountMapper userAccountMapper;
	@Autowired
	VerifyUserService verifyUserService;
	
	

	/**
	 * 查询用户账户信息列表
	 */
	@Override
	public R getUserAccountList(Map<String, Object> map) {
		try {
			map.put("userIdKey", StringUtil.getMapValue(map, "sys_user_id"));
			map.put("typeKey", "".equals(StringUtil.getMapValue(map, "type")) ? "all" : StringUtil.getMapValue(map, "type"));//账户类型：全部还是某种
			map.put("lastIdKey", "".equals(StringUtil.getMapValue(map, "last_id")) ? "0" : StringUtil.getMapValue(map, "last_id"));
			List<Map<String, Object>> userAccountList = userAccountMapper.getUserAccountList(map);
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			respondMap.put("userAccountList", userAccountList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- getUserAccountList方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


	/**
	 * 修改用户账户信息
	 */
	@Override
	@Transactional
	public R updateUserAccount(Map<String, Object> map) {
		//（2）参数信息校验
		R checkParamResult = this.updateUserAccountCheck(map);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		//（3）更新用户账户操作
		return this.updateUserAccountOper(map);
	}


	/**
	 * 修改用户账户操作
	 * @param map
	 * @return
	 */
	private R updateUserAccountOper(Map<String, Object> map) {
		map.put("cre_date", TimeUtil.getDayString());//创建日期
		map.put("cre_time", TimeUtil.getTimeString());//创建时间
		//操作类型：新增
		if(OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE_ADD.equals(map.get("user_account_oper").toString())) {
			return this.addUserAccount(map);
			//操作类型：编辑
		}else if(OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE_EDIT.equals(map.get("user_account_oper").toString())) {
			return this.editUserAccount(map);
			//操作类型：删除
		}else if(OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE_DEL.equals(map.get("user_account_oper").toString())) {
			return this.delUserAccount(map);
			//操作类型：设置默认
		}else {
			return this.editUserAccount(map);
		}
	}


	/**
	 * 删除用户账户
	 * @param map
	 * @return
	 */
	private R delUserAccount(Map<String, Object> map) {
		try {
			int i=0;
			i = userAccountMapper.deleteUserAccountById(map);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998, CommonCodeConstant.COMMON_MSG_999998);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- delUserAccount方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		} finally {
			//清除缓存
			redisUtils.remove(RedisNameConstants.user_account_info+StringUtil.getMapValue(map, "sys_user_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.user_account_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}


	/**
	 * 编辑用户账户
	 * @param map
	 * @return
	 */
	private R editUserAccount(Map<String, Object> map) {
		try {
			int i=0;
			//如果编辑成默认账户（先将所有账户设置成非默认账户，再编辑账户），否则直接编辑账户：即编辑账户是必须操作
			if(TypeStatusConstant.user_account_isdefault_1.equals(StringUtil.getMapValue(map, "isdefault"))) {
				//（1）将用户所有账户均设置成非默认账户
				i = userAccountMapper.updateAccountNoDefault(map);
			}
			//（2）编辑用户账户（默认和非默认方法都通用）
			i = userAccountMapper.updateUserAccount(map);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998, CommonCodeConstant.COMMON_MSG_999998);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- editUserAccount方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		} finally {
			//清除缓存
			redisUtils.remove(RedisNameConstants.user_account_id+StringUtil.getMapValue(map, "account_id"),
					RedisNameConstants.user_account_info+StringUtil.getMapValue(map, "sys_user_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.user_account_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}


	/**
	 * 新增收款账户
	 * @param map
	 * @return
	 */
	public R addUserAccount(Map<String, Object> map) {
		try {
			int i=0;
			//（1）查询系统设置的银行卡限制数量
			//int userAccountMaxNum = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_userAccountMaxNum));
			int userAccountMaxNum = 1;
			//（2）查询用户总共的账户数量
			int userAccountNum = userAccountMapper.getUserAccountNum(map);
			if(userAccountNum+1>userAccountMaxNum) {
				return R.error(CommonCodeConstant.COMMON_CODE_999968, CommonCodeConstant.COMMON_MSG_999968);
			}
			//如果要求设置成默认账户（先将所有账户设置成非默认账户，再添加账户），否则直接添加账户：即添加账户是必须操作
			if(TypeStatusConstant.user_account_isdefault_1.equals(StringUtil.getMapValue(map, "isdefault"))) {
				//（1）将用户所有账户均设置成非默认地址
				i = userAccountMapper.updateAccountNoDefault(map);
			}
			//（2）添加用户账户（默认和非默认方法都通用）
			i = userAccountMapper.addUserAccount(map);
			if(i != 1){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998, CommonCodeConstant.COMMON_MSG_999998);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- addUserAccount方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		} finally {
			redisUtils.remove(RedisNameConstants.user_account_info+StringUtil.getMapValue(map, "sys_user_id"));
			redisUtils.updateVersion(RedisNameVersionConstants.user_account_list_version+StringUtil.getMapValue(map, "sys_user_id"));
		}
	}


	/**
	 * 修改用户收货地址的参数信息校验
	 * @param map
	 * @return
	 */
	private R updateUserAccountCheck(Map<String, Object> map) {
		try {
			//前端必须把账户操作类型参数传过来
			R checkOperResult = ParamValidUtil.checkSpecifyParam(map, "user_account_oper", OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE);
			if(!Boolean.valueOf(checkOperResult.get(R.SUCCESS_TAG).toString())) {
				return checkOperResult;
			}
			//如果是新增修改账户
			if(OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE_ADD.equals(map.get("user_account_oper").toString())
				|| OperParamConstant.USER_ACCOUNT_OPER_UPDATE_TYPE_EDIT.equals(map.get("user_account_oper").toString())){
				//前端必须把账户类型参数传过来
				R checkTypeResult = ParamValidUtil.checkSpecifyParam(map, "type", OperParamConstant.USER_ACCOUNT_TYPE);
				if(!Boolean.valueOf(checkTypeResult.get(R.SUCCESS_TAG).toString())) {
					return checkTypeResult;
				}
				//如果是设置银行卡
				if(OperParamConstant.USER_ACCOUNT_TYPE_03.equals(map.get("type").toString())) {
					//必备参数表信息
					R checkMustReslut = ParamValidUtil.checkMustParam(map, OperParamConstant.USER_ACOOUNT_TYPE_03_MUST_PARAM);
					if(!Boolean.valueOf(checkMustReslut.get(R.SUCCESS_TAG).toString())) {
						return checkMustReslut;
					}
				}
				//校验短信验证码
				//（2）校验短信验证码
//				map.put("bus_type", VerifyConstant.BusType_FrontSetUserAccount);
//				R checkSmsResult = smsCodeService.checkSmsCode(map);
//				if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
//					return checkSmsResult;
//				}
				//校验支付密码
				R checkPayPassword = verifyUserService.checUserPayPass(map);
				if(!Boolean.valueOf(checkPayPassword.get(R.SUCCESS_TAG).toString())) {
					return checkPayPassword;
				}
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- updateUserAccountCheck方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
		}
	}


	/**
	 * 根据id查询用户账户详情
	 */
	@Override
	public R getUserAccountById(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			map.put("accountIdKey", StringUtil.getMapValue(map, "account_id"));
			Map<String, Object> userAccount = userAccountMapper.getUserAccountById(map);
			respondMap.put("userAccount", userAccount);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- getUserAccountById方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}
	
	
	/**
	 * 查询用户账户信息
	 */
	@Override
	public R getUserAccountInfo(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			List<Map<String, Object>> userAccountInfo = userAccountMapper.getUserAccountInfo(map);
			respondMap.put("userAccountInfo", userAccountInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- getUserAccountInfo方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	
	/**
	 * 查询用户该类型的账户信息
	 */
	@Override
	public R getUserAccountByType(Map<String, Object> map) {
		try {
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			Map<String, Object> userAccountType = userAccountMapper.getUserAccountByType(map);
			respondMap.put("userAccountType", userAccountType);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserAccountServiceImpl -- getUserAccountByType方法处理异常:"+ ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	
}
