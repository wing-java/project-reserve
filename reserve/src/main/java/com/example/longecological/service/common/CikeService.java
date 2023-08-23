package com.example.longecological.service.common;

import com.example.longecological.entity.R;

public interface CikeService {

	R dealCikeTrade(String user_id, String money, String callbackurl, String oper_type, String sys_order_no, String date, String time, String product, String user_ip);
}
