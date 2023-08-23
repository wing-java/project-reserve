package com.example.longecological.constant;

import java.math.BigDecimal;

public class SysParamConstant {

	/**
	 * token过期时间
	 */
	public static final Long past_seconds = 2592000L;
	
	/**
	 * 页面数量（分页）
	 */
	public static final int page_size = 10;

	/**
	 * 加密次数
	 */
	public static final Integer passNum = 10;
	/**
	 * 取反参数
	 */
	public static final BigDecimal lose = new BigDecimal(-1);
	/**
	 * 数值0
	 */
	public static final BigDecimal bigdecimal_0 = new BigDecimal(0);
	/**
	 * 小数精度
	 */
	public static final int decimal_precision = 8;
	/**
	 * 文票积分小数精度
	 */
	public static final int ticket_decimal_precision = 2;
	/**
	 * 图形验证码过期时间
	 */
	public static final Long verification_code_seconds = 3600L;
	/**
	 * 有效登录天数
	 */
	public static final int validLoginDay = 7;
	
	/**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
