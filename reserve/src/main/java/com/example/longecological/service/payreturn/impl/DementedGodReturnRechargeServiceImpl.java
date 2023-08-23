package com.example.longecological.service.payreturn.impl;

import com.example.longecological.config.properties.DementedGodProperties;
import com.example.longecological.constant.BenefitRecordTypeContants;
import com.example.longecological.mapper.common.DementedGodMapper;
import com.example.longecological.mapper.wallet.UserWalletMapper;
import com.example.longecological.service.payreturn.DementedGodReturnRechargeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;
@Service
public class DementedGodReturnRechargeServiceImpl implements DementedGodReturnRechargeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DementedGodReturnRechargeServiceImpl.class);

    @Autowired
    private DementedGodProperties dementedGodProperties;
    @Autowired
    private DementedGodMapper dementedGodMapper;
    @Autowired
    UserWalletMapper userWalletMapper;


    @Override
    @Transactional
    public void dementedGodAsynchronousResultRecharge(Map<String, Object> params) {
        int i = 0;
        try{
            // 参数处理
            if(StringUtil.getMapValue(params, "order_status").equals("1")){
                Map<String, Object> signMap = new HashMap<>();
                signMap.put("amount",params.get("amount"));
                signMap.put("body",params.get("body"));
                signMap.put("channel",params.get("channel"));
                signMap.put("trade_no",params.get("trade_no"));
                signMap.put("out_trade_no",params.get("out_trade_no"));
                signMap.put("order_status",params.get("order_status"));
                String sign = SignUtil.getSignToDementedGod(signMap,dementedGodProperties.getMd5key());
                // 验签
                if(sign.equals(params.get("sign"))){
                    //查询订单
                    Map<String, Object> order = dementedGodMapper.getDementedGodTradeByOrderNo(StringUtil.getMapValue(params, "out_trade_no"));
                    if(order == null) throw new Exception("未查询到订单信息");
                    //更新用户余额
                    String date = TimeUtil.getDayString();
                    String time = TimeUtil.getTimeString();
                    Map<String, Object> edit_user = new HashMap<>();
                    edit_user.put("user_id", StringUtil.getMapValue(order, "user_id"));//用户编号
                    edit_user.put("op_order_no", StringUtil.getMapValue(order, "sys_order_no"));//订单号
                    edit_user.put("op_type", BenefitRecordTypeContants.benefit_record_type_01);//操作类型：购买产品
                    edit_user.put("balance_num", params.get("amount"));//支付余额账户
                    edit_user.put("up_date", date);//更新日期
                    edit_user.put("up_time", time);//更新时间
                    i = userWalletMapper.updateUserWalletNum(edit_user);
                    if(i != 1){
                        throw new Exception("用户余额更新异常");
                    }
                    //更新订单状态
                    Map<String, Object> edit_order = new HashMap<>();
                    edit_order.put("id", StringUtil.getMapValue(order, "id"));
                    edit_order.put("status", "09");
                    edit_order.put("pay_datetime", params.get("body"));
                    edit_order.put("up_date", date);//更新日期
                    edit_order.put("up_time", time);//更新时间
                    i = dementedGodMapper.updateOrderStatus(edit_order);
                    if(i != 1){
                        throw new Exception("订单状态更新异常");
                    }
                    //更新充值订单状态
                    Map<String, Object> edit_recharge = new HashMap<>();
                    edit_recharge.put("order_no", StringUtil.getMapValue(order, "sys_order_no"));
                    edit_recharge.put("new_status", "09");
                    edit_recharge.put("old_status", "00");
                    edit_recharge.put("up_date", date);//更新日期
                    edit_recharge.put("up_time", time);//更新时间
                    i = dementedGodMapper.updateRechargeOrderStatus(edit_recharge);
                    if(i != 1){
                        throw new Exception("订单状态更新异常");
                    }
                }else{
                    // 验签失败
                    throw new Exception("验签失败");
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("DementedGodReturnRechargeServiceImpl -- dementedGodAsynchronousResultRecharge 方法执行异常:"+ ExceptionUtil.getExceptionAllinformation(e));
        }
    }
}
