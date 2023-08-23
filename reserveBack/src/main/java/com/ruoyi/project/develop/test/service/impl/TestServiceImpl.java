package com.ruoyi.project.develop.test.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.RocketMqConstants;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.project.develop.test.mapper.TestMapper;
import com.ruoyi.project.develop.test.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	private static final Logger Logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private TestMapper testMapper;
	@Autowired
	RocketMQTemplate rocketMQTemplate;

	@Override
	public void test(String date) {
		List<Map<String, Object>> list = testMapper.getOrderList();
		if(list!=null && list.size()>0) {
			for(Map<String, Object> obj : list) {
				JSONObject json = new JSONObject();
				json.put("id", StringUtil.getMapValue(obj, "id"));
				json.put("date", date.substring(0, 8));
				json.put("time", date.substring(8));
				rocketMQTemplate.syncSend(RocketMqConstants.order_benefit_day_topic,json.toJSONString());
				rocketMQTemplate.syncSend(RocketMqConstants.order_benefit_year_topic,json.toJSONString());
				System.out.println("---------------test----------------:"+json.toJSONString());
			}
		}
	}

}
