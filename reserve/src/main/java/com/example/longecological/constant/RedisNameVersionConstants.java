package com.example.longecological.constant;

public class RedisNameVersionConstants {
	
	/**
	 * redis前缀名称
	 */
	public static final String redis_name_version_key_prefix = "reserve_";
	
	/**
	 * 商品分类版本号
	 */
	public static final String goods_category_list_version = redis_name_version_key_prefix + "goods_category_list_version";
	
	
	/**
	 * 订单列表版本号
	 */
	public static final String order_list_version = redis_name_version_key_prefix + "order_list_version_";
	
	
	/**
	 * 用户地址列表版本号
	 */
	public static final String user_address_list_version = redis_name_version_key_prefix + "user_address_list_version_";
	
	
	/**
	 * 用户意见反馈版本号
	 */
	public static final String user_feedback_list_version = redis_name_version_key_prefix + "user_feedback_list_version_";
	
	
	/**
	 * 用户收款账户版本号
	 */
	public static final String user_account_list_version = redis_name_version_key_prefix + "user_account_list_version_";
	
	/**
	 * 用户提现记录版本号
	 */
	public static final String user_cash_record_list_version = redis_name_version_key_prefix + "user_cash_record_list_version_";
	
	
	/**
	 * 系统公告版本号
	 */
	public static final String sys_notice_info_list_version = redis_name_version_key_prefix + "sys_notice_info_list_version_";
	/**
	 * 系统新闻版本号
	 */
	public static final String sys_news_info_list_version = redis_name_version_key_prefix + "sys_news_info_list_version_";
	
	
	/**
	 * 用户线下充值记录列表版本号
	 */
	public static final String user_recharge_offline_list_version = redis_name_version_key_prefix + "user_recharge_offline_list_version_";
	/**
	 * 用户线上充值记录列表版本号
	 */
	public static final String user_recharge_online_list_version = redis_name_version_key_prefix + "user_recharge_online_list_version_";
	
	
	/**
	 * 用户线下捐赠记录列表版本号
	 */
	public static final String user_donate_offline_list_version = redis_name_version_key_prefix + "user_donate_offline_list_version_";
	/**
	 * 用户线上捐赠记录列表版本号
	 */
	public static final String user_donate_online_list_version = redis_name_version_key_prefix + "user_donate_online_list_version_";
	
	
	/**
	 * 平台产品列表版本号
	 */
	public static final String sys_product_list_version = redis_name_version_key_prefix + "sys_product_list_version_";
	
	/**
	 * 用户产品订单列表版本号
	 */
	public static final String user_product_order_list_version = redis_name_version_key_prefix + "user_product_order_list_version_";
	
}
