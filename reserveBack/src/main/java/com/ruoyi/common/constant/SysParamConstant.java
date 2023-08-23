package com.ruoyi.common.constant;

import java.math.BigDecimal;

/**
 * 系统参数
 * @author Administrator
 *
 */
public class SysParamConstant {
	
	
	/**
	 * 七牛公钥
	 */
	public static final String qiniu_accessKey = "";
	/**
	 * 七牛私钥
	 */
	public static final String qiniu_secretKey = "";
	/**
	 * 七牛域名
	 */
	public static final String qiniu_domain = "";
	/**
	 * 七牛对象名
	 */
	public static final String qiniu_bucket = "";
	
	
	/**
	 * 图形验证码过期时间
	 */
	public static final Long verification_code_seconds = 3600L;
	
	/**
	 * 一天对应的秒数
	 */
	public static final Long day_seconds = 86400L;
	
	/**
	 * 加密次数
	 */
	public static final Integer passNum = 10;

	/**
	 * 小数精度
	 */
	public static final int decimal_precision = 2;
	
	/**
	 * 小数精度
	 */
	public static final int score_decimal_precision = 2;
	
	/**
	 * 小数精度
	 */
	public static final int ticket_decimal_precision = 2;
	
	/**
	 * 初始化BigDecimal 0， -1， 100
	 */
	public static final BigDecimal bigDecimal_0 = new BigDecimal("0");
	public static final BigDecimal bigDecimal_lose = new BigDecimal("-1");
	public static final BigDecimal bigDecimal_100 = new BigDecimal("100");
	
	public static final String sys_domain = "https://gyhwugs.gjzlcb.com";


}
