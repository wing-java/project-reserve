package com.ruoyi.project.develop.task.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.config.properties.AlinProperties;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.task.mapper.UserCashTaskMapper;
import com.ruoyi.project.develop.task.service.UserCashTaskService;
import com.ruoyi.project.develop.trade.mapper.UserCashMapper;
import com.ruoyi.project.develop.user.mapper.UserInfoMapper;

@Service
public class UserCashTaskServiceImpl implements UserCashTaskService {
	
	@Autowired
	UserCashTaskMapper userCashTaskMapper;
	@Autowired
	AlinProperties alinProperties;
	@Autowired
	UserCashMapper userCashMapper;
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public void dealUserCashTaskSatus() {
		List<Map<String, Object>> order_list = userCashTaskMapper.getUserCashTaskSatus02();
		if(order_list!=null && order_list.size()>0) {
			for(Map<String, Object> order : order_list) {
				SpringUtils.getAopProxy(this).updateUserCashTaskSatus(order);
			}
		}
		
	}
	
	@Transactional
	public void updateUserCashTaskSatus(Map<String, Object> order) {
		try {
			//查询状态
			Map<String, Object> data3 = new HashMap<>();
			data3.put("app_id", alinProperties.getAppid());
			data3.put("out_trade_no", StringUtil.getMapValue(order, "order_id"));
			data3.put("time", new Date().getTime()/1000);
			String sign3 = SignUtil.getSign(data3, false, false, alinProperties.getMd5key()).toLowerCase();
			data3.put("sign", sign3);
			String result3 = HttpRequest.sendPost(alinProperties.getUrl()+"/api/payment/status", JSON.toJSONString(data3));
			System.out.println(result3);
			JSONObject alin_json = JSONObject.parseObject(result3);
			int i = 0;
			if("200".equals(alin_json.getString("code"))) {
				JSONObject obj = alin_json.getJSONObject("data");
				if(obj.getInteger("trade_status") == 1) {
					//（2）更新提现状态
					Map<String, Object> cashMap = new HashMap<>();
					cashMap.put("cash_id", order.get("id").toString());
					cashMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_02);
					cashMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_09);
					cashMap.put("old_task_status", TypeStatusConstant.user_cash_cash_status_02);
					cashMap.put("new_task_status", TypeStatusConstant.user_cash_cash_status_09);
					cashMap.put("trade_no", alin_json.getString("trade_no"));
					cashMap.put("complete_time", alin_json.getString("complete_time"));
					cashMap.put("message", alin_json.getString("message"));
					cashMap.put("cre_date", TimeUtil.getDayString());
					cashMap.put("cre_time", TimeUtil.getTimeString());
					i = userCashMapper.updateUserCashTaskStatus(cashMap);
					if(i != 1) {
						throw new Exception("提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
					}
					//（3）记录提现流水
					cashMap.put("note", "提现处理成功");
					cashMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_09);
					i = userCashMapper.addUserCashDetail(cashMap);
					if(i != 1) {
						throw new Exception("提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
					}
				}else if(obj.getInteger("trade_status") == 2) {
					//（2）更新提现状态
					Map<String, Object> cashMap = new HashMap<>();
					cashMap.put("cash_id", order.get("id").toString());
					cashMap.put("old_cash_status", TypeStatusConstant.user_cash_cash_status_02);
					cashMap.put("new_cash_status", TypeStatusConstant.user_cash_cash_status_08);
					cashMap.put("old_task_status", TypeStatusConstant.user_cash_cash_status_02);
					cashMap.put("new_task_status", TypeStatusConstant.user_cash_cash_status_08);
					cashMap.put("trade_no", alin_json.getString("trade_no"));
					cashMap.put("complete_time", alin_json.getString("complete_time"));
					cashMap.put("message", alin_json.getString("message"));
					cashMap.put("cre_date", TimeUtil.getDayString());
					cashMap.put("cre_time", TimeUtil.getTimeString());
					i = userCashMapper.updateUserCashTaskStatus(cashMap);
					if(i != 1) {
						throw new Exception("提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
					}
					//（3）记录提现流水
					cashMap.put("note", "提现处理失败");
					cashMap.put("cash_detail_status", TypeStatusConstant.user_cash_cash_status_08);
					i = userCashMapper.addUserCashDetail(cashMap);
					if(i != 1) {
						throw new Exception("提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
					}
					//（4）退还提现数量
					cashMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_14);//用户提现失败退还
					cashMap.put("op_order_no", order.get("order_id"));
					cashMap.put("user_id", order.get("user_id"));
					cashMap.put("balance_num", new BigDecimal("0"));
					if(TypeStatusConstant.user_cash_account_type_01.equals(order.get("account_type").toString())) {
						cashMap.put("balance_num", new BigDecimal(order.get("cash_money").toString()));
					}
					i = userInfoMapper.updateUserWallet(cashMap);
					if(i != 1) {
						throw new Exception("提现编号"+cashMap.get("cash_id").toString()+"：用户账户更新失败");
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
}
