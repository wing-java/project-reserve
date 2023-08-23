package com.ruoyi.project.develop.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.encryption.md5.SignUtil;
import com.ruoyi.common.utils.interfaces.HttpRequest;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.config.properties.AlinProperties;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.trade.domain.UserCash;
import com.ruoyi.project.develop.trade.mapper.UserCashMapper;
import com.ruoyi.project.develop.trade.service.UserCashService;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;


/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
@Service
public class UserCashServiceImpl implements UserCashService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserCashMapper userCashMapper;
	@Autowired
	AlinProperties alinProperties;

	
	/**
	 * 查询用户取现记录列表
	 */
	@Override
	public List<Map<String, Object>> getUserCashList(Map<String, Object> params) {
		return userCashMapper.getUserCashList(params);
	}
	/**
	 * 汇总取现记录
	 */
	@Override
	public Map<String, Object> summaryUserCashList(Map<String, Object> params) {
		return userCashMapper.summaryUserCashList(params);
	}
	/**
	 * 导出用户取现记录列表
	 */
	@Override
	public List<UserCash> selectUserCashList(Map<String, Object> params) {
		return userCashMapper.selectUserCashList(params);
	}
	/**
	 * 查询取现记录详情列表
	 */
	@Override
	public List<Map<String, Object>> getUserCashDetailList(Map<String, Object> params) {
		return userCashMapper.getUserCashDetailList(params);
	}


	/**
	 * 导出用户待处理提现记录
	 */
	@Override
	public List<UserCash> selectWaitUserCashList(Map<String, Object> params) {
		List<UserCash> agentUserCashListDeal = userCashMapper.selectUserCashList(params);
		List<UserCash> agentUserCashList = new ArrayList<>();
		int num = 0;
		if(agentUserCashListDeal != null && agentUserCashListDeal.size() > 0){
			for(UserCash bean : agentUserCashListDeal){
				try{
					Map<String, Object> cashDealMap = new HashMap<String, Object>();
					cashDealMap.put("cash_id", bean.getId());//编号
					cashDealMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_00);//旧状态：待处理
					cashDealMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_02);//旧状态：处理中
					cashDealMap.put("cre_date", TimeUtil.getDayString());
					cashDealMap.put("cre_time", TimeUtil.getTimeString());
					cashDealMap.put("create_by", ShiroUtils.getLoginName());
					//（1）更新取现状态
					num = userCashMapper.updateUserCashStatus(cashDealMap);
					if(num != 1){
						continue;
					}
					//（2）记录提现流水
					cashDealMap.put("note", "提现处理中");
					cashDealMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_02);
					userCashMapper.addUserCashDetail(cashDealMap);
					agentUserCashList.add(bean);
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
			}
		}
		return agentUserCashList;
	}


	/**
	 * 批量审核处理处理中的提现记录
	 */
	@Override
	public R batchSysAuditUserCash(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_cash_cash_status_09.equals(StringUtil.getMapValue(params, "cash_status"))) {
        	//批量提现成功
        	return this.batchSuccessUserCash(params);
        }else if(TypeStatusConstant.user_cash_cash_status_08.equals(StringUtil.getMapValue(params, "cash_status"))){
        	//批量提现失败
        	return this.batchFaileUserCash(params);
        }else if(TypeStatusConstant.user_cash_cash_status_02.equals(StringUtil.getMapValue(params, "cash_status"))){
        	//批量提现失败
        	return this.batchPayUserCash(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	/**
	 * 批量提现成功（更新到账金额、结算单笔手续费金额、结算比例手续费金额）
	 * @param params
	 * @return
	 */
	public R batchSuccessUserCash(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] cash_ids = Convert.toStrArray(StringUtil.getMapValue(params, "cash_ids"));
        for(int i=0;i<cash_ids.length;i++) {
        	Map<String, Object> cashMap = new HashMap<>();
        	cashMap.put("remark", params.get("remark"));
        	cashMap.put("cash_id", cash_ids[i]);
        	//依次处理每一个
        	R result = SpringUtils.getAopProxy(this).successUserCash(cashMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 处理单个提现记录（处理中=====》处理成功）
	 * 
	 * @param cashMap
	 * @return
	 */
	@Transactional
	public R successUserCash(Map<String, Object> cashMap) {
		try {
			int i=0;
			//（1）查询提现详情
			Map<String, Object> cashDetailMap = userCashMapper.getUserCashById(cashMap.get("cash_id").toString());
			if(!TypeStatusConstant.user_cash_cash_status_00.equals(StringUtil.getMapValue(cashDetailMap, "cash_status"))) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态已变更");
			}
			//（2）更新提现状态
			cashMap.put("cash_id", cashDetailMap.get("id").toString());
			cashMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_00);
			cashMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_09);
			cashMap.put("update_by", ShiroUtils.getLoginName());
			cashMap.put("cre_date", TimeUtil.getDayString());
			cashMap.put("cre_time", TimeUtil.getTimeString());
			i = userCashMapper.updateUserCashStatus(cashMap);
			if(i != 1) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
			}
			//（3）记录提现流水
			cashMap.put("note", "提现处理成功");
			cashMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_09);
			i = userCashMapper.addUserCashDetail(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：更新异常");
		}
	}
	/**
	 * 批量提现失败
	 * @param params
	 * @return
	 */
	public R batchFaileUserCash(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] cash_ids = Convert.toStrArray(StringUtil.getMapValue(params, "cash_ids"));
        for(int i=0;i<cash_ids.length;i++) {
        	Map<String, Object> cashMap = new HashMap<>();
        	cashMap.put("remark", params.get("remark"));
        	cashMap.put("cash_id", cash_ids[i]);
        	//依次处理每一个
        	R result = SpringUtils.getAopProxy(this).faileUserCash(cashMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 处理单个提现记录（处理中=====》处理失败）
	 * @param cashMap
	 * @return
	 */
	@Transactional
	public R faileUserCash(Map<String, Object> cashMap) {
		try {
			int i=0;
			//（1）查询提现详情
			Map<String, Object> cashDetailMap = userCashMapper.getUserCashById(cashMap.get("cash_id").toString());
			if(!TypeStatusConstant.user_cash_cash_status_00.equals(StringUtil.getMapValue(cashDetailMap, "cash_status"))) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态已变更");
			}
			//（2）更新提现状态
			cashMap.put("cash_id", cashDetailMap.get("id").toString());
			cashMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_00);
			cashMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_08);
			cashMap.put("update_by", ShiroUtils.getLoginName());
			cashMap.put("cre_date", TimeUtil.getDayString());
			cashMap.put("cre_time", TimeUtil.getTimeString());
			i = userCashMapper.updateUserCashStatus(cashMap);
			if(i != 1) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
			}
			//（3）记录提现流水
			cashMap.put("note", "提现处理成功");
			cashMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_08);
			i = userCashMapper.addUserCashDetail(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
			}
			//（4）退还提现数量
			cashMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_14);//用户提现失败退还
			cashMap.put("op_order_no", cashDetailMap.get("order_id"));
			cashMap.put("user_id", cashDetailMap.get("user_id"));
			cashMap.put("balance_num", new BigDecimal("0"));
			if(TypeStatusConstant.user_cash_account_type_01.equals(cashDetailMap.get("account_type").toString())) {
				cashMap.put("balance_num", new BigDecimal(cashDetailMap.get("cash_money").toString()));
			}
			i = userInfoMapper.updateUserWallet(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：用户账户更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：更新异常");
		}
	}
	
	
	/**
	 * 批量提现成功（更新到账金额、结算单笔手续费金额、结算比例手续费金额）
	 * @param params
	 * @return
	 */
	public R batchPayUserCash(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] cash_ids = Convert.toStrArray(StringUtil.getMapValue(params, "cash_ids"));
        for(int i=0;i<cash_ids.length;i++) {
        	Map<String, Object> cashMap = new HashMap<>();
        	cashMap.put("remark", params.get("remark"));
        	cashMap.put("cash_id", cash_ids[i]);
        	//依次处理每一个
        	R result = SpringUtils.getAopProxy(this).payUserCash(cashMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	/**
	 * 处理单个提现记录（处理中=====》处理成功）
	 * 
	 * @param cashMap
	 * @return
	 */
	@Transactional
	public R payUserCash(Map<String, Object> cashMap) {
		try {
			int i=0;
			//（1）查询提现详情
			Map<String, Object> cashDetailMap = userCashMapper.getUserCashById(cashMap.get("cash_id").toString());
			if(!TypeStatusConstant.user_cash_cash_status_00.equals(StringUtil.getMapValue(cashDetailMap, "cash_status"))) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态已变更");
			}
			//（2）更新提现状态
			cashMap.put("cash_id", cashDetailMap.get("id").toString());
			cashMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_00);
			cashMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_02);
			cashMap.put("old_task_status", TypeStatusConstant.user_cash_cash_status_00);
			cashMap.put("new_task_status", TypeStatusConstant.user_cash_cash_status_02);
			cashMap.put("update_by", ShiroUtils.getLoginName());
			cashMap.put("cre_date", TimeUtil.getDayString());
			cashMap.put("cre_time", TimeUtil.getTimeString());
			i = userCashMapper.updateUserCashTaskStatus(cashMap);
			if(i != 1) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
			}
			//（3）记录提现流水
			cashMap.put("note", "提现处理中");
			cashMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_02);
			i = userCashMapper.addUserCashDetail(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
			}
			//提交第三方
			Map<String, Object> data2 = new HashMap<>();
			data2.put("app_id", alinProperties.getAppid());
			data2.put("product_id", alinProperties.getProductid());
			data2.put("out_trade_no", StringUtil.getMapValue(cashDetailMap, "order_id"));
			data2.put("amount", StringUtil.getMapValue(cashDetailMap, "arrival_money"));
			data2.put("time", new Date().getTime()/1000);
			String sign2 = SignUtil.getSign(data2, false, false, alinProperties.getMd5key()).toLowerCase();
			data2.put("sign", sign2);
			JSONObject ext = new JSONObject();
			ext.put("accountName", StringUtil.getMapValue(cashDetailMap, "account_name"));
			ext.put("accountNumber", StringUtil.getMapValue(cashDetailMap, "account"));
			ext.put("bankName", StringUtil.getMapValue(cashDetailMap, "bank_name"));
			data2.put("ext", ext);
			String result2 = HttpRequest.sendPost(alinProperties.getUrl()+"/api/payment", JSON.toJSONString(data2));
			System.out.println(result2);
			JSONObject ali_json = JSONObject.parseObject(result2);
			if(!"200".equals(ali_json.getString("code"))) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：代付异常，"+ali_json.getString("message"));
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：更新异常");
		}
	}
	
}
