package com.ruoyi.project.develop.user.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamCodeConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.encryption.md5.MD5Utils;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.RegexUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.benefit.domain.Benefit;
import com.ruoyi.project.develop.benefit.mapper.BenefitBalanceMapper;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.param.mapper.SysEditMapper;
import com.ruoyi.project.develop.param.service.SysParamService;
import com.ruoyi.project.develop.statistics.domain.SummaryUserRegisterEvery;
import com.ruoyi.project.develop.user.domain.UserInfo;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;
import com.ruoyi.project.develop.user.service.UserInfoService;


/**
 * 用户信息管理
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private SysEditMapper sysEditMapper;
	
	@Autowired
	private BenefitBalanceMapper benefitBalanceMapper;
	


	
	/**
	 * 查询用户列表
	 */
	@Override
	public List<Map<String, Object>> getUserInfoList(Map<String, Object> params) {
		return userInfoMapper.getUserInfoList(params);
	}
	/**
	 * 统计用户信息
	 */
	@Override
	public Map<String, Object> summaryUserInfoList(Map<String, Object> params) {
		return userInfoMapper.summaryUserInfoList(params);
	}
	/**
	 * 导出用户列表
	 */
	@Override
	public List<UserInfo> selectUserInfoList(Map<String, Object> params) {
		return userInfoMapper.selectUserInfoList(params);
	}
	/**
	 * 根据用户id查询用户详情
	 */
	@Override
	public UserInfo getUserInfoById(String id) {
		return userInfoMapper.getUserInfoById(id);
	}


	/**
	 * 批量系统冻结解冻用户账户
	 */
	@Override
	public R batchSysFreezeUser(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		/*if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }*/
        if(TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(params, "status"))) {
        	//解冻账号
        	return this.batchNoFreezeUser(params);
        }else if(TypeStatusConstant.user_info_status_1.equals(StringUtil.getMapValue(params, "status"))){
        	//冻结账号
        	return this.batchFreezeUser(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	/**
	 * 批量冻结账号
	 * @param params
	 * @return
	 */
	private R batchFreezeUser(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("remark", params.get("remark"));
        	userMap.put("user_id", user_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).freezeUser(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "冻结结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "冻结结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 单个账号冻结
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R freezeUser(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）更新用户状态
			userMap.put("old_status", TypeStatusConstant.user_info_status_0);
			userMap.put("new_status", TypeStatusConstant.user_info_status_1);
			userMap.put("cre_date", TimeUtil.getDayString());
			userMap.put("cre_time", TimeUtil.getTimeString());
			i = userInfoMapper.updateUserStatus(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：状态更新失败");
			}
			//（2）记录冻结账号
			i = userInfoMapper.addFrozeUser(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结记录记录失败");
			}
			//（3）缓存清理
			redisUtils.remove(RedisNameConstants.user_freeze+userMap.get("user_id").toString());
			redisUtils.remove(RedisNameConstants.user_info_id+userMap.get("user_id").toString());
			
			return R.ok("冻结成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结异常");
		}
	}
	/**
	 * 批量解冻账号
	 * @param params
	 * @return
	 */
	private R batchNoFreezeUser(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("remark", params.get("remark"));
        	userMap.put("user_id", user_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).noFreezeUser(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "解冻结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "解冻结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 单个账号解冻
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R noFreezeUser(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）更新用户状态
			userMap.put("old_status", TypeStatusConstant.user_info_status_1);
			userMap.put("new_status", TypeStatusConstant.user_info_status_0);
			userMap.put("cre_date", TimeUtil.getDayString());
			userMap.put("cre_time", TimeUtil.getTimeString());
			i = userInfoMapper.updateUserStatus(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：状态更新失败");
			}
			//（2）删除冻结账号
			i = userInfoMapper.deleteFrozeUser(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结记录删除失败");
			}
			//（3）缓存清理
			redisUtils.remove(RedisNameConstants.user_freeze+userMap.get("user_id").toString());
			redisUtils.remove(RedisNameConstants.user_info_id+userMap.get("user_id").toString());
			
			return R.ok("解冻成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：解冻异常");
		}
	}


	/**
	 * 修改保存用户信息
	 */
	@Override
	@Transactional
	public R editUserInfo(Map<String, Object> params) {
		try {
			System.out.println(params);
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			/*if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}*/
			//校验推广码
			if(!RegexUtil.isValidEightNum(StringUtil.getMapValue(params, "uid2"))) {
				return R.error(Type.WARN, "推广码必需使用8位整数");
			}
			int count = userInfoMapper.getRegisterNumByUid(params);
			if(count > 0) {
				return R.error(Type.WARN, "该推广码已被其他用户使用，无法修改");
			}
			params.put("check", "check");
			//注册类型：手机注册
			if(TypeStatusConstant.user_info_register_type_1.equals(StringUtil.getMapValue(params, "register_type"))) {
				//校验手机格式
				if(!RegexUtil.isValidTel(StringUtil.getMapValue(params, "user_account"))) {
					return R.error(Type.WARN, "手机号码格式有误");
				}
				params.put("user_tel", params.get("user_account").toString());
				params.put("user_email", null);
				//系统参数：一个手机号注册人数
			    Integer telRegisterNum = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_telRegisterNum));
				//查询该手机号注册人数
			    Integer telHaveRegisterNum = userInfoMapper.getRegisterNumByUserTel(params.get("user_account").toString());
				if(telHaveRegisterNum > telRegisterNum) {
					return R.error(Type.WARN, "已达到该手机号最大注册人数");
				}
			}else if(TypeStatusConstant.user_info_register_type_2.equals(StringUtil.getMapValue(params, "register_type"))) {
				//校验邮箱格式
				if(!StringUtil.containString(StringUtil.getMapValue(params, "user_account"), "@")) {
					return R.error(Type.WARN, "邮箱账号格式有误");
				}
				params.put("user_email", params.get("user_account").toString());
				params.put("user_tel", null);
				//系统参数：一个邮箱注册人数
			    Integer emailRegisterNum = Integer.parseInt(sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_emailRegisterNum));
				//查询该手机号注册人数
			    Integer emailHaveRegisterNum = userInfoMapper.getRegisterNumByUserEmail(params.get("user_account").toString());
				if(emailHaveRegisterNum > emailRegisterNum) {
					return R.error(Type.WARN, "已达到该邮箱最大注册人数");
				}
			}else {
				return R.error(Type.WARN, "注册类型异常");
			}
			//账号校验
			Map<String, Object> userInfoMap = userInfoMapper.getUserMapBySysUserAccount(params.get("sys_user_account").toString());
			if(userInfoMap!=null && !params.get("user_id").toString().equals(userInfoMap.get("id").toString())) {
				return R.error(Type.WARN, "该账号已存在");
			}
			//（1）查询old参数信息
			Map<String, Object> oldValue = userInfoMapper.getUserMapById(params.get("user_id").toString());
			//处理登录密码信息
			if(!StringUtils.isEmpty(StringUtil.getMapValue(params, "login_password"))) {
				if(StringUtil.getMapValue(params, "login_password").length()<6) {
					return R.error(Type.WARN, "密码格式不对");
				}
				params.put("login_password", new Md5Hash(oldValue.get("cre_date").toString()
						+oldValue.get("cre_time").toString(), 
						MD5Utils.MD5Encode(params.get("login_password").toString()).toUpperCase(), 
						SysParamConstant.passNum).toString());
			}
			//处理支付密码信息
			if(!StringUtils.isEmpty(StringUtil.getMapValue(params, "pay_password"))) {
				params.put("pay_password", new Md5Hash(oldValue.get("cre_date").toString()
						+oldValue.get("cre_time").toString(), 
						MD5Utils.MD5Encode(params.get("pay_password").toString()).toUpperCase(), 
						SysParamConstant.passNum).toString());
			}
			//（2）更新用户信息
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = userInfoMapper.updateUserInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "用户信息更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = userInfoMapper.getUserMapById(params.get("user_id").toString());
			//（4）记录修改记录
			params.put("table_name", SysTableNameConstant.t_user_info);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = sysEditMapper.addSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			//（4）清理缓存
			redisUtils.remove(RedisNameConstants.user_info_id+params.get("user_id").toString());
			
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "参数不匹配，用户信息更新异常");
		}
	}

	
	/**
	 * 查询父级团队成员列表
	 */
	@Override
	public List<Map<String, Object>> getParentUserInfoList(Map<String, Object> params) {
		return userInfoMapper.getParentUserInfoList(params);
	}
	/**
	 * 导出父级成员信息列表
	 */
	@Override
	public List<UserInfo> selectParentUserInfoList(Map<String, Object> params) {
		return userInfoMapper.selectParentUserInfoList(params);
	}
	/**
	 * 统计父级成员数据信息
	 */
	@Override
	public Map<String, Object> summaryParentUserInfoList(Map<String, Object> params) {
		return userInfoMapper.summaryParentUserInfoList(params);
	}


	/**
	 * 查询子级团队成员列表
	 */
	@Override
	public List<Map<String, Object>> getChildrenUserList(Map<String, Object> params) {
		return userInfoMapper.getChildrenUserList(params);
	}
	/**
	 * 导出子级成员信息列表
	 */
	@Override
	public List<UserInfo> selectChildrenUserInfoList(Map<String, Object> params) {
		return userInfoMapper.selectChildrenUserInfoList(params);
	}
	/**
	 * 统计子级成员数据信息
	 */
	@Override
	public Map<String, Object> summaryChildrenUserInfoList(Map<String, Object> params) {
		return userInfoMapper.summaryChildrenUserInfoList(params);
	}
	
	
	/**
	 * 实时统计用户注册信息
	 */
	@Override
	public List<Map<String, Object>> getSummaryUserRegisterList(Map<String, Object> params) {
		return userInfoMapper.getSummaryUserRegisterList(params);
	}
	/**
	 * 导出实时注册信息
	 */
	@Override
	public List<SummaryUserRegisterEvery> selectSummaryUserRegisterList(Map<String, Object> params) {
		return userInfoMapper.selectSummaryUserRegisterList(params);
	}
	/**
	 * 实时图表统计分析用户注册量
	 */
	@Override
	public List<Map<String, Object>> getUserRegisterList() {
		return userInfoMapper.getUserRegisterList();
	}


	/**
	 * 查询用户流水详情列表
	 */
	@Override
	public List<Map<String, Object>> getBenefitRecordList(Map<String, Object> params) {
		return benefitBalanceMapper.getBenefitRecordBalanceList(params);
	}
	
	
	/**
	 * 导出余额流水记录
	 */
	@Override
	public List<Benefit> selectBenefitRecordBalanceList(Map<String, Object> params) {
		return benefitBalanceMapper.selectBenefitRecordBalanceList(params);
	}

	
	/**
	 * 平台钱包信息统计
	 */
	@Override
	public Map<String, Object> getPlatformPurseInfo() {
		return userInfoMapper.getPlatformPurseInfo();
	}

	/**
	 * 平移用户
	 */
	@Override
	@Transactional
	public R transUser(Map<String, Object> params) {
		int num = 0;
		try {
			/*******************开始处理用户关系********************************/
			//获取当前用户信息
			Map<String, Object> userMap = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(params, "id"));
			//查询当前父级用户
			Map<String, Object> oldParentMap = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(userMap, "referer_id"));
			//查询需要平移后的父级信息
			Map<String, Object> newParentMap = userInfoMapper.getUserByUserTel(params);
			if(newParentMap == null) {
				return R.error("未查询到用户信息");
			}
			if(oldParentMap!=null) {
				if(Integer.parseInt(StringUtil.getMapValue(newParentMap, "id")) == Integer.parseInt(StringUtil.getMapValue(oldParentMap, "id"))) {
					return R.error("当前用户已是指定用户推荐人");
				}
			}
			//更新父级直推人数和伞下人数
			Map<String, Object> param = new HashMap<>();
			
			//更新旧父级
			if(oldParentMap != null) {
				param.put("refer_num", -1);
				param.put("under_num", -1-Integer.parseInt(StringUtil.getMapValue(userMap, "under_num")));
				param.put("user_id", StringUtil.getMapValue(userMap, "referer_id"));
				num = userInfoMapper.updateUserReferUnderNum(param);
				if(num != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("父级信息更新失败");
				}
				//清除旧父级缓存
				redisUtils.remove(RedisNameConstants.user_info_id+oldParentMap.get("id").toString());
				
				//查询旧父级的父级链成员
				List<Map<String, Object>> parentUserList = userInfoMapper.getParentChainUserList(StringUtil.getMapValue(oldParentMap, "parent_chain"));
				if(parentUserList!=null && parentUserList.size()>0) {
					for(Map<String, Object> parentUser : parentUserList) {
						param.put("refer_num", 0);
						param.put("under_num", -1-Integer.parseInt(StringUtil.getMapValue(userMap, "under_num")));
						param.put("user_id", StringUtil.getMapValue(parentUser, "id"));
						num = userInfoMapper.updateUserReferUnderNum(param);
						if(num != 1) {
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return R.error("父级信息更新失败");
						}
						//清除旧父级缓存
						redisUtils.remove(RedisNameConstants.user_info_id+parentUser.get("id").toString());
					}
				}
			}
			
			//更新新父级
			param.put("refer_num", 1);
			param.put("under_num", Integer.parseInt(StringUtil.getMapValue(userMap, "under_num"))+1);
			param.put("user_id", StringUtil.getMapValue(newParentMap, "id"));
			num = userInfoMapper.updateUserReferUnderNum(param);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error("父级信息更新失败");
			}
			//清除新父级缓存
			redisUtils.remove(RedisNameConstants.user_info_id+newParentMap.get("id").toString());
			
			//查询旧父级的父级链成员
			List<Map<String, Object>> parentUserList2 = userInfoMapper.getParentChainUserList(StringUtil.getMapValue(newParentMap, "parent_chain"));
			if(parentUserList2!=null && parentUserList2.size()>0) {
				for(Map<String, Object> parentUser : parentUserList2) {
					param.put("refer_num", 0);
					param.put("under_num", Integer.parseInt(StringUtil.getMapValue(userMap, "under_num"))+1);
					param.put("user_id", StringUtil.getMapValue(parentUser, "id"));
					num = userInfoMapper.updateUserReferUnderNum(param);
					if(num != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return R.error("父级信息更新失败");
					}
					//清除旧父级缓存
					redisUtils.remove(RedisNameConstants.user_info_id+parentUser.get("id").toString());
				}
			}
			
			//更新当前用户父级及父级链以及代数
			param.put("user_id", StringUtil.getMapValue(userMap, "id"));
			param.put("algebra", Integer.parseInt(StringUtil.getMapValue(newParentMap, "algebra"))-Integer.parseInt(StringUtil.getMapValue(oldParentMap, "algebra")));
			param.put("referer_id", StringUtil.getMapValue(newParentMap, "id"));
			String old_parent_chain = StringUtil.getMapValue(userMap, "parent_chain");
			String new_parent_chain;
			if(StringUtil.isEmpty(StringUtil.getMapValue(newParentMap, "parent_chain"))) {
				new_parent_chain = StringUtil.getMapValue(newParentMap, "id");
			}else {
				new_parent_chain = StringUtil.getMapValue(newParentMap, "parent_chain") + "," + StringUtil.getMapValue(newParentMap, "id");
			}
			//更新当前用户
			param.put("parent_chain", new_parent_chain);
			num = userInfoMapper.updateUserReferInfo(param);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error("推荐信息更新失败");
			}
			//清除当前用户级缓存
			redisUtils.remove(RedisNameConstants.user_info_id+userMap.get("id").toString());
			
			//查询伞下用户列表
			List<Map<String, Object>> underUserList = userInfoMapper.getUnderUserList(StringUtil.getMapValue(userMap, "id"));
			if(underUserList!=null && underUserList.size()>0) {
				for(Map<String, Object> underUser : underUserList) {
					param.put("user_id", StringUtil.getMapValue(underUser, "id"));
					param.put("referer_id", StringUtil.getMapValue(underUser, "referer_id"));
					String parent_chain_str = "," + StringUtil.getMapValue(underUser, "parent_chain") + ",";
					String parent_chain_str1 = "," + old_parent_chain + ",";
					String parent_chain_str2 = "," + new_parent_chain + ",";
					String parent_chain_str3 = parent_chain_str.replace(parent_chain_str1, parent_chain_str2);
					param.put("parent_chain", parent_chain_str3.substring(1, parent_chain_str3.length()-1));
					//更新当前用户
					num = userInfoMapper.updateUserReferInfo(param);
					if(num != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return R.error("推荐信息更新失败");
					}
					//清除当前用户级缓存
					redisUtils.remove(RedisNameConstants.user_info_id+underUser.get("id").toString());
				}
			}
			return R.ok("操作成功");
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error("操作异常");
		}
		
	}
	
	
	/**
	 * 新增业绩
	 */
	@Override
	@Transactional
	public R addPerformance(Map<String, Object> params) {
		int num = 0;
		try {
			/*******************校验用户信息************************************/
			//校验个人业绩
			if(new BigDecimal(StringUtil.getMapValue(params, "person_performance")).compareTo(SysParamConstant.bigDecimal_0)<0) {
				return R.error("业绩金额不能为负");
			}
			/*******************开始处理用户关系********************************/
			//获取当前用户信息
			Map<String, Object> userMap = userInfoMapper.getRealUserInfoById(StringUtil.getMapValue(params, "id"));
			//更新用户金额
			Map<String, Object> data = new HashMap<>();
			data.put("user_id", StringUtil.getMapValue(userMap, "id"));
			data.put("person_performance", StringUtil.getMapValue(params, "person_performance"));
			data.put("up_date", TimeUtil.getDayString());
			data.put("up_time", TimeUtil.getTimeString());
			num = userInfoMapper.updateUserPerformance(data);
			if(num != 1) {
				return R.error("会员新增业绩更新异常");
			}
			//判断是否有父级
			if(!StringUtil.isEmpty(StringUtil.getMapValue(userMap, "parent_chain"))) {
				Map<String, Object> data1= new HashMap<>();
				data1.put("parent_chain", StringUtil.getMapValue(userMap, "parent_chain"));
				data1.put("team_performance",StringUtil.getMapValue(params, "person_performance"));
				data1.put("up_date", TimeUtil.getDayString());
				data1.put("up_time", TimeUtil.getTimeString());
				num = userInfoMapper.updateUserPerformanceParent(data1);
				if(num != StringUtil.getMapValue(userMap, "parent_chain").split(",").length) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error("会员新增业绩更新异常");
				}
			}
			//增加新增业绩记录
			Map<String, Object> record = new HashMap<>();
			record.put("user_id", StringUtil.getMapValue(userMap, "id"));
			record.put("person_performance", StringUtil.getMapValue(params, "person_performance"));
			record.put("team_performance", StringUtil.getMapValue(params, "team_performance"));
			record.put("cre_date", TimeUtil.getDayString());
			record.put("cre_time", TimeUtil.getTimeString());
			record.put("remark", StringUtil.getMapValue(params, "remark"));
			record.put("create_by", ShiroUtils.getSysUser().getLoginName());//创建人
			num = userInfoMapper.addUserSysPerformance(record);
			if(num != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error("会员新增业绩记录异常");
			}
			return R.ok("操作成功");
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error("操作异常");
		}
		
	}
}
