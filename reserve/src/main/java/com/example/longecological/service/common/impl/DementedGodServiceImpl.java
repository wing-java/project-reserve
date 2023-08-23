package com.example.longecological.service.common.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.config.properties.DementedGodProperties;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.OrderInfoConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.common.DementedGodMapper;
import com.example.longecological.service.common.DementedGodService;
import com.example.longecological.service.user.impl.UserRechargeServiceImpl;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.http.HttpClient;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class DementedGodServiceImpl implements DementedGodService {
    @Autowired
    private DementedGodProperties dementedGodProperties;
    @Autowired
    private DementedGodMapper dementedGodMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(DementedGodServiceImpl.class);
    @Override
    public R dealDementedGodTrade(String user_id, String money, String oper_type, String sys_order_no, String date, String time,String product,String body,String userIp) {
        //（2）封装统一支付接口并调用
        Map<String, Object> data = new HashMap<>();
        data.put("mchid", dementedGodProperties.getMchId());
        data.put("out_trade_no", StringUtil.getDateTimeAndRandomForID());
        data.put("amount", money);
        data.put("channel", product);
        data.put("time_stamp", TimeUtil.getDate());
        data.put("body", body);
        data.put("return_url", dementedGodProperties.getReturnUrl());
        data.put("notify_url", dementedGodProperties.getNotifyUrl());
        // 生成sign
        String sign = SignUtil.getSignToDementedGod(data, dementedGodProperties.getMd5key());
        data.put("sign", sign);
        LOGGER.info("请求参数：" + data.toString());
        String result = HttpClient.sendHttpPost(dementedGodProperties.getUrl(), data);
        JSONObject ret_json = JSONObject.parseObject(result);
        LOGGER.info("返回参数：" + ret_json.toJSONString());
        if(!"0".equals(ret_json.getString("code"))) {
            return R.error(OrderInfoConstant.ORDER_INFO_CODE_999285, ret_json.getString("msg"));
        }
        //保存充值记录
        JSONObject ret_data_json = ret_json.getJSONObject("data");
        data.put("user_ip",userIp);
        data.put("user_id", user_id);
        data.put("recharge_url", ret_data_json.getString("request_url"));
        data.put("oper_type", oper_type);
        data.put("sys_order_no", sys_order_no);
        data.put("cre_date", date);
        data.put("cre_time", time);
        int num = dementedGodMapper.insertDementedGodTrade(data);
        if(num != 1) {
            return R.error(OrderInfoConstant.ORDER_INFO_CODE_999287, OrderInfoConstant.ORDER_INFO_MSG_999287);
        }
        return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,ret_data_json.getString("request_url"));
    }
}
