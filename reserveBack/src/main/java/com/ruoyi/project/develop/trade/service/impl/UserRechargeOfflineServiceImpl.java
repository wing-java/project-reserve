package com.ruoyi.project.develop.trade.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.constant.RedisNameVersionConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.trade.domain.UserRechargeOffline;
import com.ruoyi.project.develop.trade.mapper.UserRechargeOfflineMapper;
import com.ruoyi.project.develop.trade.service.UserRechargeOfflineService;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;


/**
 * 用户线下充值管理
 * @author Administrator
 *
 */
@Service
public class UserRechargeOfflineServiceImpl implements UserRechargeOfflineService {
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	private UserRechargeOfflineMapper userRechargeOfflineMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;

	
	/**
	 * 查询用户线下充值列表
	 */
	@Override
	public List<Map<String, Object>> getUserRechargeOfflineList(Map<String, Object> params) {
		return userRechargeOfflineMapper.getUserRechargeOfflineList(params);
	}
	/**
	 * 导出用户线下充值列表
	 */
	@Override
	public List<UserRechargeOffline> selectUserRechargeOfflineList(Map<String, Object> params) {
		return userRechargeOfflineMapper.selectUserRechargeOfflineList(params);
	}
	/**
	 * 统计充值信息
	 */
	@Override
	public Map<String, Object> summaryUserRechargeOfflineList(Map<String, Object> params) {
		return userRechargeOfflineMapper.summaryUserRechargeOfflineList(params);
	}
	

	/**
	 * 批量审核用户线下充值记录
	 */
	@Override
	public R batchSysAuditUserRechargeOffline(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		/*if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }*/
        if(TypeStatusConstant.user_recharge_info_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//充值成功
        	return this.batchSuccessUserRechargeOffline(params);
        }else if(TypeStatusConstant.user_recharge_info_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//充值失败
        	return this.batchFaileUserRechargeOffline(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	/**
	 * 批量审核充值失败：用户线下充值
	 * @param params
	 * @return
	 */
	private R batchFaileUserRechargeOffline(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] recharge_ids = Convert.toStrArray(StringUtil.getMapValue(params, "recharge_ids"));
        for(int i=0;i<recharge_ids.length;i++) {
        	Map<String, Object> rechargeMap = new HashMap<>();
        	rechargeMap.put("remark", params.get("remark"));
        	rechargeMap.put("recharge_id", recharge_ids[i]);
        	R result = SpringUtils.getAopProxy(this).faileUserRechargeOffline(rechargeMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 审核充值失败：用户线下充值
	 * @param rechargeMap
	 * @return
	 */
	@Transactional
	public R faileUserRechargeOffline(Map<String, Object> rechargeMap) {
		try {
			//（1）根据充值编号查询充值详情
			Map<String, Object> rechargeDetail  = userRechargeOfflineMapper.getUserRechargeOfflineById(rechargeMap.get("recharge_id").toString());
			if(rechargeDetail==null || !TypeStatusConstant.user_recharge_info_status_04.equals(StringUtil.getMapValue(rechargeDetail, "status"))) {
				return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"审核信息无效");
			}
			//（2）更新充值状态
			int i=0;
			rechargeMap.put("old_status", TypeStatusConstant.user_recharge_info_status_04);//旧状态：待审核
			rechargeMap.put("new_status", TypeStatusConstant.user_recharge_info_status_08);//新状态：充值失败
			rechargeMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			rechargeMap.put("up_date", TimeUtil.getDayString());
			rechargeMap.put("up_time", TimeUtil.getTimeString());
			i = userRechargeOfflineMapper.updateUserRechargeOfflineStatus(rechargeMap);
			if(i != 1) {
				return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"：状态更新失败");
			}
			//（3）清除缓存
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_offline_list_version+StringUtil.getMapValue(rechargeDetail, "user_id"));
			return R.ok("审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"：审核异常");
		}
	}
	/**
	 * 批量审核充值成功：用户线下充值
	 * @param params
	 * @return
	 */
	private R batchSuccessUserRechargeOffline(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] recharge_ids = Convert.toStrArray(StringUtil.getMapValue(params, "recharge_ids"));
        for(int i=0;i<recharge_ids.length;i++) {
        	Map<String, Object> rechargeMap = new HashMap<>();
        	rechargeMap.put("remark", params.get("remark"));
        	rechargeMap.put("recharge_id", recharge_ids[i]);
        	R result = SpringUtils.getAopProxy(this).successUserRechargeOffline(rechargeMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 审核充值成功：用户线下充值
	 * @param rechargeMap
	 * @return
	 */
	@Transactional
	public R successUserRechargeOffline(Map<String, Object> rechargeMap) {
		try {
			//（1）根据充值编号查询充值详情
			Map<String, Object> rechargeDetail  = userRechargeOfflineMapper.getUserRechargeOfflineById(rechargeMap.get("recharge_id").toString());
			if(rechargeDetail==null || !TypeStatusConstant.user_recharge_info_status_04.equals(StringUtil.getMapValue(rechargeDetail, "status"))) {
				return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"审核信息无效");
			}
			//（2）更新充值状态
			int i=0;
			rechargeMap.put("old_status", TypeStatusConstant.user_recharge_info_status_04);//旧状态：待审核
			rechargeMap.put("new_status", TypeStatusConstant.user_recharge_info_status_09);//新状态：充值成功
			rechargeMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			rechargeMap.put("up_date", TimeUtil.getDayString());
			rechargeMap.put("up_time", TimeUtil.getTimeString());
			i = userRechargeOfflineMapper.updateUserRechargeOfflineStatus(rechargeMap);
			if(i != 1) {
				return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"：状态更新失败");
			}
			//（3）更新用户账户余额
			rechargeMap.put("op_order_no", rechargeDetail.get("order_id"));//订单号
			rechargeMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_01);//操作类型：线下充值
			rechargeMap.put("user_id", rechargeDetail.get("user_id"));//用户id
			rechargeMap.put("balance_num", new BigDecimal(0));//充值数量
			if(TypeStatusConstant.user_recharge_online_account_type_01.equals(rechargeDetail.get("account_type").toString())) {
				rechargeMap.put("balance_num", rechargeDetail.get("recharge_num"));//充值数量
			}
			i = userInfoMapper.updateUserWallet(rechargeMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"：账户余额更新失败");
			}
			//（4）清除缓存
			redisUtils.updateVersion(RedisNameVersionConstants.user_recharge_offline_list_version+StringUtil.getMapValue(rechargeDetail, "user_id"));
			return R.ok("审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "充值编号"+rechargeMap.get("recharge_id").toString()+"：审核异常");
		}
	}
	
}
