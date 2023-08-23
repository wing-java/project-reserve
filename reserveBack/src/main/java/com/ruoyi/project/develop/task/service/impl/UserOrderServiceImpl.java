package com.ruoyi.project.develop.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.RocketMqConstants;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.UserOrderMapper;
import com.ruoyi.project.develop.task.service.UserOrderService;

@Service
public class UserOrderServiceImpl implements UserOrderService {
	
	@Autowired
	UserOrderMapper userOrderMapper;
	@Autowired
	RocketMQTemplate rocketMQTemplate;

	@Override
	public void dealUserOrder() {
		//查询阶段收益
		List<Map<String, Object>> list1 = userOrderMapper.getUserOrderDayBenefitList();
		if(list1!=null && list1.size()>0) {
			for(Map<String, Object> obj : list1) {
				JSONObject data = new JSONObject();
				data.put("id", StringUtil.getMapValue(obj, "id"));
				data.put("date", TimeUtil.getDayString());
				data.put("time", StringUtil.getMapValue(obj, "init_time2"));
				data.put("targetTopic", RocketMqConstants.order_benefit_day_topic);
				Long calTime = TimeUtil.calLastedTime(data.getString("date")+data.getString("time"), StringUtil.getMapValue(obj, "init_date2")+StringUtil.getMapValue(obj, "init_time2"), "yyyyMMddHHmmss");
				if(calTime == null) continue;
				data.put("delaySecond", calTime);
//				rocketMQTemplate.syncSend(RocketMqConstants.delay_topic,data.toJSONString());
				rocketMQTemplate.syncSend(RocketMqConstants.order_benefit_day_topic,data.toJSONString());
			}
		}
		
		//查询年度收益
		List<Map<String, Object>> list2 = userOrderMapper.getUserOrderYearBenefitList();
		if(list2!=null && list2.size()>0) {
			for(Map<String, Object> obj : list2) {
				JSONObject data = new JSONObject();
				data.put("id", StringUtil.getMapValue(obj, "id"));
				data.put("date", TimeUtil.getDayString());
				data.put("time", StringUtil.getMapValue(obj, "init_time1"));
				data.put("targetTopic", RocketMqConstants.order_benefit_year_topic);
				Long calTime = TimeUtil.calLastedTime(data.getString("date")+data.getString("time"), StringUtil.getMapValue(obj, "init_date1")+StringUtil.getMapValue(obj, "init_time1"), "yyyyMMddHHmmss");
				if(calTime == null) continue;
				data.put("delaySecond", calTime);
//				rocketMQTemplate.syncSend(RocketMqConstants.delay_topic,data.toJSONString());
				rocketMQTemplate.syncSend(RocketMqConstants.order_benefit_year_topic,data.toJSONString());
			}
		}
	}

	@Override
	public void dealUserOrderSettle() {
		List<Map<String, Object>> list1 = userOrderMapper.getUserOrderSettleList();
		if(list1!=null && list1.size()>0) {
			for(Map<String, Object> obj : list1) {
				Map<String, Object> record = new HashMap<>();
				record.put("order_id", StringUtil.getMapValue(obj, "id"));
				record.put("order_no", StringUtil.getMapValue(obj, "order_no"));
				record.put("user_id", StringUtil.getMapValue(obj, "user_id"));
				record.put("goods_num", StringUtil.getMapValue(obj, "goods_num"));
				record.put("cash_num", StringUtil.getMapValue(obj, "cash_num"));
				record.put("cre_date", StringUtil.getMapValue(obj, "cre_date"));
				record.put("cre_time", StringUtil.getMapValue(obj, "cre_time"));
				record.put("product_id", StringUtil.getMapValue(obj, "product_id"));
				record.put("goods_price", StringUtil.getMapValue(obj, "goods_price"));
				record.put("goods_name", StringUtil.getMapValue(obj, "goods_name"));
				record.put("goods_show", StringUtil.getMapValue(obj, "goods_show"));
				record.put("sharestock_num", StringUtil.getMapValue(obj, "sharestock_num"));
				record.put("sharestock_no", StringUtil.getMapValue(obj, "sharestock_no"));
				record.put("real_name", StringUtil.getMapValue(obj, "real_name"));
				record.put("status", "09");
				rocketMQTemplate.syncSend(RocketMqConstants.order_reward_topic,JSON.toJSONString(record));
			}
		}
	}

	public static void main(String[] args) {
		long time = TimeUtil.calLastedTime("20230728162000", "20230728161000", "yyyyMMddHHmmss");
		System.out.println(time);
	}
}
