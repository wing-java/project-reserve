package com.example.longecological.service.async.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.async.AsyncBenefitYearService;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class AsyncBenefitYearServiceImpl implements AsyncBenefitYearService {
	
	@Autowired
	AsyncBenefitMapper asyncBenefitMapper;
	@Autowired
	UserWalletMapper userWalletMapper;

	@Override
	public void dealUserOrderYearBenefit(Map<String, Object> order, String date, String time) throws Exception {
		//计算当前订单时间
		long days = TimeUtil.getDays(date+time, StringUtil.getMapValue(order, "init_date1")+StringUtil.getMapValue(order, "init_time1"), "yyyyMMddHHmmss");
		//查询需要结算的收益比例
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", StringUtil.getMapValue(order, "user_id"));
		param.put("buy_order_id", StringUtil.getMapValue(order, "id"));
		param.put("days", days);
		Map<String, Object> yearMap = asyncBenefitMapper.getUserOrderYearBenefitRate(param);
		if(yearMap == null) return;
		//计算收益
		BigDecimal benefit = new BigDecimal(StringUtil.getMapValue(order, "cash_num")).multiply(new BigDecimal(StringUtil.getMapValue(yearMap, "rate")));
		//更新用户收益
		String order_no = StringUtil.getDateTimeAndRandomForID();
		this.editUserWallet(order, benefit, order_no);
		//记录收益日志
		this.addUserRewardToYear(order, yearMap, benefit, order_no);
		//更新订单
		this.updateOrderBenefit(StringUtil.getMapValue(order, "id"), benefit, StringUtil.getMapValue(yearMap, "year"), date, time);
	}

	public void updateOrderBenefit(String id, BigDecimal benefit, String year, String date, String time) throws Exception{
		Map<String, Object> edit_order = new HashMap<>();
		edit_order.put("id", id);
		if("3".equals(year)) {
			edit_order.put("is_end", "1");
		}else {
			edit_order.put("is_end", "0");
		}
		edit_order.put("total_benefit1", benefit);
		edit_order.put("up_date", date);
		edit_order.put("up_time", time);
		int num = asyncBenefitMapper.updateOrderYearBenefit(edit_order);
		if(num != 1) throw new Exception("结束状态更新异常");
	}

	public void addUserRewardToYear(Map<String, Object> order, Map<String, Object> yearMap, BigDecimal benefit,
			String order_no) throws Exception{
		Map<String, Object> record = new HashMap<>();
		record.put("user_id", StringUtil.getMapValue(order, "user_id"));
		record.put("order_no", order_no);
		record.put("benefit", benefit);
		record.put("buy_order_no", StringUtil.getMapValue(order, "order_no"));
		record.put("buy_order_id", StringUtil.getMapValue(order, "id"));
		record.put("year", StringUtil.getMapValue(yearMap, "year"));
		record.put("days", StringUtil.getMapValue(yearMap, "days"));
		record.put("rate", StringUtil.getMapValue(yearMap, "rate"));
		record.put("cre_date", TimeUtil.getDayString());
		record.put("cre_time", TimeUtil.getTimeString());
		int num = asyncBenefitMapper.addUserRewardToYear(record);
		if(num != 1) throw new Exception("年收益日志记录异常");
	}

	public void editUserWallet(Map<String, Object> order, BigDecimal benefit, String order_no) throws Exception{
		Map<String, Object> edit_user = new HashMap<>();
		edit_user.put("user_id", StringUtil.getMapValue(order, "user_id"));
		edit_user.put("balance_num", benefit);
		edit_user.put("total_benefit", benefit);
		edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_13);
		edit_user.put("op_order_no", order_no);
		edit_user.put("up_date", TimeUtil.getDayString());
		edit_user.put("up_time", TimeUtil.getTimeString());
		int num = userWalletMapper.updateUserWalletNum(edit_user);
		if(num != 1) throw new Exception("年收益更新异常");
	}

}
