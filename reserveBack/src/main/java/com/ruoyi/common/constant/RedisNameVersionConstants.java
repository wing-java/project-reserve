package com.ruoyi.common.constant;

public class RedisNameVersionConstants {
	
	/**
	 * redis前缀名称
	 */
	public static final String redis_name_version_key_prefix = "reserve_";
	

	/**
	 * 商品列表版本号
	 */
	public static final String goods_list_version = redis_name_version_key_prefix + "goods_list_version";
	/**
	 * 商品热销列表版本号
	 */
	public static final String goods_index_hot_list_version = redis_name_version_key_prefix + "goods_index_hot_list_version";
	/**
	 * 用户推荐商品列表版本号
	 */
	public static final String goods_user_hot_list_version = redis_name_version_key_prefix + "goods_user_hot_list_version";
	/**
	 * 商品分类版本号
	 */
	public static final String goods_category_list_version = redis_name_version_key_prefix + "goods_category_list_version";
	
	
	/**
	 * 商品评价列表
	 */
	public static final String goods_evaluate_list_version = redis_name_version_key_prefix + "goods_evaluate_list_version_";

	
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
	 * 用户收藏夹列表版本号
	 */
	public static final String user_goods_collect_info_list_version = redis_name_version_key_prefix + "user_goods_collect_info_list_version_";
	
	
	/**
	 * 用户足迹列表版本号
	 */
	public static final String user_goods_track_info_list_version = redis_name_version_key_prefix + "user_goods_track_info_list_version_";
	
	
	/**
	 * 用户购物车列表版本号
	 */
	public static final String user_goods_cart_info_list_version = redis_name_version_key_prefix + "user_goods_cart_info_list_version_";
	
	
	/**
	 * 用户收款账户版本号
	 */
	public static final String user_account_list_version = redis_name_version_key_prefix + "user_account_list_version_";
	
	
	/**
	 * 用户提现记录版本号
	 */
	public static final String user_cash_record_list_version = redis_name_version_key_prefix + "user_cash_record_list_version_";
	/**
	 * 孝道基金提现记录版本号
	 */
	public static final String user_piety_cash_record_list_version = redis_name_version_key_prefix + "user_piety_cash_record_list_version_";
	
	/**
	 * 专项提现记录版本号
	 */
	public static final String user_special_cash_record_list_version = redis_name_version_key_prefix + "user_special_cash_record_list_version_";
	
	
	/**
	 * 用户转出日志列表版本号
	 */
	public static final String user_roll_out_log_list_version = redis_name_version_key_prefix + "user_roll_out_log_list_version_";
	/**
	 * 用户转入日志列表版本号
	 */
	public static final String user_roll_in_log_list_version = redis_name_version_key_prefix + "user_roll_in_log_list_version_";
	
	
	/**
	 * 系统公告版本号
	 */
	public static final String sys_notice_info_list_version = redis_name_version_key_prefix + "sys_notice_info_list_version_";
	/**
	 * 系统新闻版本号
	 */
	public static final String sys_news_info_list_version = redis_name_version_key_prefix + "sys_news_info_list_version_";
	
	/**
	 * 平台产品列表版本号
	 */
	public static final String sys_product_list_version = redis_name_version_key_prefix + "sys_product_list_version_";
	
	/**
	 * 用户产品订单列表版本号
	 */
	public static final String user_product_order_list_version = redis_name_version_key_prefix + "user_product_order_list_version_";
	
}
