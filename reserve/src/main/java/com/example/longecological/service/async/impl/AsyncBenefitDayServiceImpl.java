package com.example.longecological.service.async.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.mapper.async.AsyncBenefitMapper;
import com.example.longecological.service.async.AsyncBenefitDayService;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;

@Service
public class AsyncBenefitDayServiceImpl implements AsyncBenefitDayService {
	
	@Autowired
	AsyncBenefitMapper asyncBenefitMapper;

	@Override
	public void dealUserOrderDayBenefit(Map<String, Object> order, String date, String time) throws Exception {
		//计算当前订单时间
		long days = TimeUtil.getDays(date+time, StringUtil.getMapValue(order, "init_date2")+StringUtil.getMapValue(order, "init_time2"), "yyyyMMddHHmmss");
		//查询需要结算的收益比例
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", StringUtil.getMapValue(order, "user_id"));
		param.put("buy_order_id", StringUtil.getMapValue(order, "id"));
		param.put("days", days);
		Map<String, Object> dayMap = asyncBenefitMapper.getUserOrderDayBenefitRate(param);
		if(dayMap == null) return;
		//计算收益
		BigDecimal benefit = new BigDecimal(StringUtil.getMapValue(order, "cash_num")).multiply(new BigDecimal(StringUtil.getMapValue(dayMap, "rate")));
		if(benefit.compareTo(new BigDecimal(StringUtil.getMapValue(order, "unclaimed_benefit")))>0) {
			//更新订单待领取收益
			Map<String, Object> edit_order = new HashMap<>();
			edit_order.put("id", StringUtil.getMapValue(order, "id"));
			edit_order.put("old_unclaimed_benefit", StringUtil.getMapValue(order, "unclaimed_benefit"));
			edit_order.put("new_unclaimed_benefit", benefit);
			edit_order.put("unclaimed_days", StringUtil.getMapValue(dayMap, "days"));
			edit_order.put("unclaimed_rate", StringUtil.getMapValue(dayMap, "rate"));
			edit_order.put("date", date);
			edit_order.put("time", time);
			int num = asyncBenefitMapper.updateOrderUnclaimedBenefit(edit_order);
			if(num != 1) throw new Exception("待领取收益更新异常");
		}
		
	}

}
