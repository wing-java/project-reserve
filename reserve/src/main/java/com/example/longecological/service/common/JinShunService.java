package com.example.longecological.service.common;

import com.example.longecological.entity.R;

public interface JinShunService {

	R dealJinShunTrade(String user_id, String money, String oper_type, String sys_order_no, String date, String time, String product);
}
