package com.ruoyi.project.develop.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.project.develop.task.mapper.UserBenefitMapper;
import com.ruoyi.project.develop.task.service.UserBenefitService;

@Service
public class UserBenefitServiceImpl implements UserBenefitService {
	
	@Autowired
	UserBenefitMapper userBenefitMapper;

	@Override
	public void dealUserBenefit() {
		List<Map<String, Object>> benefit_list = userBenefitMapper.getBenefitRecordAll();
		if(benefit_list!=null && benefit_list.size()>0) {
			for(Map<String, Object> benefit : benefit_list) {
				SpringUtils.getAopProxy(this).updateUserBenefit(benefit);
			}
		}
	}
	
	@Transactional
	public void updateUserBenefit(Map<String, Object> benefit) {
		int num = 0;
		try {
			//获取用户类型
			String column = this.getUserBenefitColumn(StringUtil.getMapValue(benefit, "purse_type"), StringUtil.getMapValue(benefit, "type"));
			if(StringUtil.isEmpty(column)) return;
			//更新用户总汇总
			Map<String, Object> edit_benefit_all = new HashMap<String, Object>();
			edit_benefit_all.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
			edit_benefit_all.put("column", column);
			edit_benefit_all.put("benefit", StringUtil.getMapValue(benefit, "num"));
			edit_benefit_all.put("up_date", StringUtil.getMapValue(benefit, "cre_date"));
			edit_benefit_all.put("up_time", StringUtil.getMapValue(benefit, "cre_time"));
			num = userBenefitMapper.updateUserBenefitAll(edit_benefit_all);
			if(num != 1) throw new Exception("用户总汇总流水统计异常");
			//查询每日流水
			int count = userBenefitMapper.getUserBenefitEveryday(StringUtil.getMapValue(benefit, "user_id"), StringUtil.getMapValue(benefit, "cre_date"));
			Map<String, Object> edit_benefit_everyday = new HashMap<>();
			edit_benefit_everyday.put("user_id", StringUtil.getMapValue(benefit, "user_id"));
			edit_benefit_everyday.put("column", column);
			edit_benefit_everyday.put("benefit", StringUtil.getMapValue(benefit, "num"));
			edit_benefit_everyday.put("date", StringUtil.getMapValue(benefit, "cre_date"));
			edit_benefit_everyday.put("time", StringUtil.getMapValue(benefit, "cre_time"));
			if(count < 1) {
				num = userBenefitMapper.addUserBenefitEveryday(edit_benefit_everyday);
				if(num != 1) throw new Exception("用户每日汇总流水新增异常");
			}else {
				num = userBenefitMapper.updateUserBenefitEveryday(edit_benefit_everyday);
				if(num != 1) throw new Exception("用户每日汇总流水更新异常");
			}
			//删除记录
			num = userBenefitMapper.deleteUserBenefit(StringUtil.getMapValue(benefit, "id"));
			if(num != 1) throw new Exception("用户待汇总流水删除异常");
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	public String getUserBenefitColumn(String purse_type, String type) {
		if(TypeStatusConstant.sys_purse_type_01.equals(purse_type)) {
			return "balance_"+"type_"+type;
		}else {
			return null;
		}
	}

}
