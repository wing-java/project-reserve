package com.ruoyi.common.constant;


/**
 * 类型状态常量管理
 * @author Administrator
 *
 */
public class TypeStatusConstant {
	
	
	/**
	 * 是否允许并发执行：允许
	 */
	public static final String sys_task_concurrent1 = "0";
	/**
	 * 是否允许并发执行：禁止
	 */
	public static final String sys_task_concurrent2 = "1";
	
	/**
	 * 用户账号状态：可用（未冻结）
	 */
	public static final String user_info_status_0 = "0";
	/**
	 * 用户账号状态：不可用（已冻结）
	 */
	public static final String user_info_status_1 = "1";
	
	
	/**
	 * 用户注册类型：手机注册
	 */
	public static final String user_info_register_type_1 = "1";
	/**
	 * 用户注册类型：邮箱注册
	 */
	public static final String user_info_register_type_2 = "2";

	
	/**
	 * 平台流水汇总类型：总汇总
	 */
	public static final String summary_user_benefit_everyday_type_1 = "1";
	/**
	 * 平台流水汇总类型：明细汇总
	 */
	public static final String summary_user_benefit_everyday_type_2 = "2";
	
	
	/**
	 * APP图片类型：首页轮播图
	 */
	public static final String sys_app_img_type_01 = "01";
	
	
	/**
	 * 设备类型：iOS
	 */
	public static final String user_info_device_type_iOS = "iOS";
	/**
	 * 设备类型：android
	 */
	public static final String user_info_device_type_android = "android";
	
	
	/**
	 * 系统钱包类型：余额（balance_num）
	 */
	public static final String sys_purse_type_01 = "01";
	
	
	/**
	 * 流水变化类型：余额（balance_num）
	 */
	public static final String sys_benefit_name_change_type_01 = "01";
	
	
	/**
	 * 系统操作标识：修改邮箱
	 */
	public static final String sys_oper_flag_modify_email_account = "modify_email_account";
	
	
	
	/**
	 * 调度任务类型：普通轮循类任务
	 */
	public static final String sys_job_job_type_01 = "01";
	/**
	 * 调度任务类型：只执行一次的任务
	 */
	public static final String sys_job_job_type_02 = "02";

	
	/**
	 * 商品状态：待上架
	 */
	public static final String goods_info_goods_status_00 = "00";
	/**
	 * 商品状态：违规下架
	 */
	public static final String goods_info_goods_status_04 = "04";
	/**
	 * 商品状态：已下架
	 */
	public static final String goods_info_goods_status_08 = "08";
	/**
	 * 商品状态：已上架
	 */
	public static final String goods_info_goods_status_09 = "09";
	
	
	/**
	 * 商品删除状态：未删除
	 */
	public static final String goods_info_del_status_0 = "0";
	/**
	 * 商品删除状态：已删除
	 */
	public static final String goods_info_del_status_1 = "1";
	
	
	/**
	 * 用户订单状态：待付款
	 */
	public static final String user_order_status_00 = "00";
	/**
	 * 用户订单状态：待发货
	 */
	public static final String user_order_status_02 = "02";
	/**
	 * 用户订单状态：待收货
	 */
	public static final String user_order_status_03 = "03";
	/**
	 * 用户订单状态：已取消
	 */
	public static final String user_order_status_04 = "04";
	/**
	 * 用户订单状态：退款
	 */
	public static final String user_order_status_05 = "05";
	/**
	 * 用户订单状态：交易完成
	 */
	public static final String user_order_status_09 = "09";
	
	
	/**
	 * 商城订单物流是否订阅：否
	 */
	public static final String user_order_info_shipper_is_subscribe_0 = "0";
	/**
	 * 商城订单物流是否订阅：是
	 */
	public static final String user_order_info_shipper_is_subscribe_1 = "1";
	

	/**
	 * 用户充值状态：充值中，待支付
	 */
	public static final String user_recharge_info_status_00 = "00";
	/**
	 * 用户余额充值状态：订单不存
	 */
	public static final String user_recharge_info_status_02 = "02";
	/**
	 * 用户充值状态：待审核
	 */
	public static final String user_recharge_info_status_04 = "04";
	/**
	 * 用户充值状态：充值失败
	 */
	public static final String user_recharge_info_status_08 = "08";
	/**
	 * 用户充值状态：充值成功
	 */
	public static final String user_recharge_info_status_09 = "09";
	
	
	/**
	 * 用户捐赠状态：充值中，待支付
	 */
	public static final String user_donate_info_status_00 = "00";
	/**
	 * 用户捐赠状态：订单不存
	 */
	public static final String user_donate_info_status_02 = "02";
	/**
	 * 用户捐赠状态：待审核
	 */
	public static final String user_donate_info_status_04 = "04";
	/**
	 * 用户捐赠状态：充值失败
	 */
	public static final String user_donate_info_status_08 = "08";
	/**
	 * 用户捐赠状态：充值成功
	 */
	public static final String user_donate_info_status_09 = "09";
	
	
	/**
	 * 用户余额充值渠道：支付宝
	 */
	public static final String user_recharge_record_recharge_type_01 = "01";
	/**
	 * 用户余额充值渠道：微信
	 */
	public static final String user_recharge_record_recharge_type_02 = "02";
	/**
	 * 用户余额充值渠道：银行卡
	 */
	public static final String user_recharge_record_recharge_type_03 = "03";
	
	
	/**
	 * 支付宝订单交易状态：交易支付成功
	 */
	public static final String user_recharge_record_alipay_trade_status_TRADE_SUCCESS = "TRADE_SUCCESS";
	/**
	 * 支付宝订单交易状态：交易创建，等待买家付款
	 */
	public static final String user_recharge_record_alipay_trade_status_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	/**
	 * 支付宝订单交易状态：未付款，交易超时关闭
	 */
	public static final String user_recharge_record_alipay_trade_status_TRADE_CLOSED = "TRADE_CLOSED";
	/**
	 * 支付宝订单交易状态：交易结束，不可退款
	 */
	public static final String user_recharge_record_alipay_trade_status_TRADE_FINISHED = "TRADE_FINISHED";
	
	
	/**
	 * 微信订单交易状态：支付成功
	 */
	public static final String user_recharge_record_wechatpay_trade_status_SUCCESS = "SUCCESS";
	/**
	 * 微信订单交易状态：转入退款
	 */
	public static final String user_recharge_record_wechatpay_trade_status_REFUND = "REFUND";
	/**
	 * 微信订单交易状态：未支付
	 */
	public static final String user_recharge_record_wechatpay_trade_status_NOTPAY = "NOTPAY";
	/**
	 * 微信订单交易状态：已关闭
	 */
	public static final String user_recharge_record_wechatpay_trade_status_CLOSED = "CLOSED";
	/**
	 * 微信订单交易状态：已撤销（付款码支付）
	 */
	public static final String user_recharge_record_wechatpay_trade_status_REVOKED = "REVOKED";
	/**
	 * 微信订单交易状态：用户支付中（付款码支付）
	 */
	public static final String user_recharge_record_wechatpay_trade_status_USERPAYING = "USERPAYING";
	/**
	 * 微信订单交易状态：支付失败(其他原因，如银行返回失败)
	 */
	public static final String user_recharge_record_wechatpay_trade_status_PAYERROR = "PAYERROR";
	
	
	/**
	 * 系统充值银行卡状态：不可用
	 */
	public static final String sys_recharge_bankcard_status_00 = "00";
	/**
	 * 系统充值银行卡状态：可用
	 */
	public static final String sys_recharge_bankcard_status_01 = "01";
	
	
	/**
	 * 用户退款订单审核类型：审核拒绝
	 */
	public static final String user_order_info_refund_audit_type_08 = "08";
	/**
	 * 用户退款订单审核类型：审核通过
	 */
	public static final String user_order_info_refund_audit_type_09 = "09";
	
	
	
	
	/**
	 * 用户订单支付方式：支付宝
	 */
	public static final String user_order_info_order_pay_type_01 = "01";
	/**
	 * 用户订单支付方式：微信
	 */
	public static final String user_order_info_order_pay_type_02 = "02";
	/**
	 * 用户订单支付方式：提现账户余额
	 */
	public static final String user_order_info_order_pay_type_03 = "03";
	/**
	 * 用户订单支付方式：助农基金
	 */
	public static final String user_order_info_order_pay_type_04 = "04";
	/**
	 * 用户订单支付方式：提货余额
	 */
	public static final String user_order_info_order_pay_type_05 = "05";
	
	
	
	/**
	 * 用户支付状态：待支付
	 */
	public static final String user_order_info_alipay_status_00 = "00";
	/**
	 * 用户支付状态：支付失败
	 */
	public static final String user_order_info_alipay_status_08 = "08";
	/**
	 * 用户支付状态：支付成功
	 */
	public static final String user_order_info_alipay_status_09 = "09";
	
	
	
	/**
	 * 支付宝退款订单状态：发生了资金变化
	 */
	public static final String user_order_info_refund_alipay_fund_change_Y = "Y";
	/**
	 * 支付宝退款订单状态：没有发生了资金变化
	 */
	public static final String user_order_info_refund_alipay_fund_change_N = "N";
	
	
	/**
	 * 订单支付的操作类型：单个订单提交（积分兑换）
	 */
	public static final String user_orde_info_pay_oper_type_01 = "01";
	/**
	 * 订单支付的操作类型：单个订单提交（积分+奖金）
	 */
	public static final String user_orde_info_pay_oper_type_02 = "02";
	/**
	 * 订单支付的操作类型：批量订单提交（积分+奖金）
	 */
	public static final String user_orde_info_pay_oper_type_03 = "03";
	
	
	/**
	 * 用户提现状态：待处理
	 */
	public static final String user_cash_cash_status_00 = "00";
	/**
	 * 用户提现状态：处理中
	 */
	public static final String user_cash_cash_status_02 = "02";
	/**
	 * 用户提现状态：已撤销
	 */
	public static final String user_cash_cash_status_04 = "04";
	/**
	 * 用户提现状态：处理失败
	 */
	public static final String user_cash_cash_status_08 = "08";
	/**
	 * 用户提现状态：处理成功
	 */
	public static final String user_cash_cash_status_09 = "09";
	
	
	/**
	 * 用户订单结算状态：待结算
	 */
	public static final String user_order_info_settle_status_00 = "00";
	/**
	 * 用户订单结算状态：结算中
	 */
	public static final String user_order_info_settle_status_02 = "02";
	/**
	 * 用户订单结算状态：结算成功
	 */
	public static final String user_order_info_settle_status_09 = "09";
	

	/**
	 * 功能开放开关:未开放
	 */
	public static final String sys_function_lock_param_lock_0 ="0";
	/**
	 * 功能开放开关:已开放
	 */
	public static final String sys_function_lock_param_lock_1 ="1";
	
	
	/**
	 * 用户提现账户类型：余额
	 */
	public static final String user_cash_account_type_01 = "01";
	
	
	/**
	 * 用户代理状态：申请中
	 */
	public static final String user_agent_status_00 = "00";
	/**
	 * 用户代理状态：申请失败
	 */
	public static final String user_agent_status_08 = "08";
	/**
	 * 用户代理状态：申请成功
	 */
	public static final String user_agent_status_09 = "09";
	
	
	/**
	 * 兑换状态：申请中
	 */
	public static final String user_exchange_status_00 = "00";
	/**
	 * 兑换状态：兑换失败
	 */
	public static final String user_exchange_status_08 = "08";
	/**
	 * 兑换状态：兑换成功
	 */
	public static final String user_exchange_status_09 = "09";
	
	
	/**
	 * 用户线上账户类型：余额
	 */
	public static final String user_recharge_online_account_type_01 = "01";
	
	/**
	 * 异步任务00-待处理
	 */
	public static final String async_task_status_00 = "00";
	/**
	 * 异步任务02-处理中
	 */
	public static final String async_task_status_02 = "02";
	/**
	 * 异步任务08-处理失败
	 */
	public static final String async_task_status_08 = "08";
	/**
	 * 异步任务09-处理成功
	 */
	public static final String async_task_status_09 = "09";
	
	/**
	 * 地区级别：省
	 */
	public static final String sys_area_area_level_1 = "1";
	/**
	 * 地区级别：市
	 */
	public static final String sys_area_area_level_2 = "2";
	
	/**
	 * 用户提现状态：待处理
	 */
	public static final String user_cash_record_cash_status_00 = "00";
	/**
	 * 用户提现状态：处理中
	 */
	public static final String user_cash_record_cash_status_02 = "02";
	/**
	 * 用户提现状态：已撤销
	 */
	public static final String user_cash_record_cash_status_04 = "04";
	/**
	 * 用户提现状态：处理失败
	 */
	public static final String user_cash_record_cash_status_08 = "08";
	/**
	 * 用户提现状态：处理成功
	 */
	public static final String user_cash_record_cash_status_09 = "09";
	
	/**
	 * 用户提现账户类型：余额
	 */
	public static final String user_cash_record_account_type_01 = "01";
	
}
