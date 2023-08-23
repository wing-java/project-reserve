package com.example.longecological.service.common;

import com.example.longecological.entity.R;

public interface DementedGodService {
    /**
     * 狂神支付
     * @param user_id
     * @param money
     * @param oper_type
     * @param sys_order_no
     * @param date
     * @param time
     * @param product
     * @param body 宝转卡 必须传支付宝用户名 否则无法到账其它请填１２３
     * @param userIp
     * @return
     */
    R dealDementedGodTrade(String user_id, String money, String oper_type, String sys_order_no, String date, String time,String product,String body,String userIp);
}
