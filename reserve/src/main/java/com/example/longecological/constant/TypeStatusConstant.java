package com.example.longecological.constant;

public class TypeStatusConstant {
	
	/**
	 * 用户状态：未冻结
	 */
	public static final String user_info_status_0 ="0";
	/**
	 * 用户状态：已冻结
	 */
	public static final String user_info_status_1 ="1";
	
	
	/**
	 * 注册类型：手机号
	 */
	public static final String user_info_register_type_1 ="1";
	/**
	 * 注册类型：邮箱
	 */
	public static final String user_info_register_type_2 ="2";
	
	
	/**
	 * 用户收货地址：非默认
	 */
	public static final String user_address_isdefault_0 = "0";
	/**
	 * 用户收货地址：默认
	 */
	public static final String user_address_isdefault_1 = "1";
	

	/**
	 * 商品状态：审核中
	 */
	public static final String goods_info_goods_status_00 = "00";
	/**
	 * 商品状态：违规下架
	 */
	public static final String goods_info_goods_status_04 = "04";
	/**
	 * 商品状态：审核失败
	 */
	public static final String goods_info_goods_status_06 = "06";
	/**
	 * 商品状态：已下架
	 */
	public static final String goods_info_goods_status_08 = "08";
	/**
	 * 商品状态：销售中
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
	 * 功能开放开关:未开放
	 */
	public static final String sys_function_lock_param_lock_0 ="0";
	/**
	 * 功能开放开关:已开放
	 */
	public static final String sys_function_lock_param_lock_1 ="1";
	
	
	/**
	 * 用户订单支付方式：现金券
	 */
	public static final String user_order_order_pay_type_01 = "01";
	/**
	 * 用户订单支付方式：积分
	 */
	public static final String user_order_order_pay_type_02 = "02";
	
	
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
	 * 用户订单退款状态：待申请
	 */
	public static final String user_order_refund_status_00 = "00";
	/**
	 * 用户订单退款状态：待审核
	 */
	public static final String user_order_refund_status_04 = "04";
	/**
	 * 用户订单退款状态：退款中
	 */
	public static final String user_order_refund_status_06 = "06";
	/**
	 * 用户订单退款状态：退款失败
	 */
	public static final String user_order_refund_status_07 = "07";
	/**
	 * 用户订单退款状态：审核失败
	 */
	public static final String user_order_refund_status_08 = "08";
	/**
	 * 用户订单退款状态：退款成功
	 */
	public static final String user_order_refund_status_09 = "09";
	
	
	/**
	 * 用户订单评价状态：待评价
	 */
	public static final String user_order_evaluate_status_0 = "0";
	/**
	 * 用户订单评价状态：已评价
	 */
	public static final String user_order_evaluate_status_1 = "1";
	/**
	 * 用户订单评价状态：已删除
	 */
	public static final String user_order_evaluate_status_2 = "2";
	
	
	/**
	 * 用户支付状态：待支付
	 */
	public static final String user_order_alipay_status_00 = "00";
	/**
	 * 用户支付状态：支付失败
	 */
	public static final String user_order_alipay_status_08 = "08";
	/**
	 * 用户支付状态：支付成功
	 */
	public static final String user_order_alipay_status_09 = "09";
	
	
	/**
	 * 购物车商品信息是否变更：否
	 */
	public static final String user_shop_cart_info_is_change_0 = "0";
	/**
	 * 购物车商品信息是否变更：是
	 */
	public static final String user_shop_cart_info_is_change_1 = "1";
	
	
	/**
	 * 收藏夹操作类型：取消收藏
	 */
	public static final String user_goods_collect_info_oper_type_0 = "0";
	/**
	 * 收藏夹操作类型：添加收藏
	 */
	public static final String user_goods_collect_info_oper_type_1 = "1";
	
	
	/**
	 * 店铺关注操作类型：取消关注
	 */
	public static final String user_supplier_focus_oper_type_0 = "0";
	/**
	 * 店铺关注操作类型：添加关注
	 */
	public static final String user_supplier_focus_oper_type_1 = "1";
	
	
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
	 * 用户账户：非默认
	 */
	public static final String user_account_isdefault_0 = "0";
	/**
	 * 用户账户：默认
	 */
	public static final String user_account_isdefault_1 = "1";
	
	
	/**
	 * 用户账户类型：银行卡
	 */
	public static final String user_account_type_1 = "1";
	/**
	 * 用户账户类型：支付宝
	 */
	public static final String user_account_type_2 = "2";
	/**
	 * 用户账户类型：微信
	 */
	public static final String user_account_type_3 = "3";
	
	
	/**
	 * 我的团队：直推
	 */
	public static final String user_team_team_type_01 = "01";
	/**
	 * 我的团队：间推
	 */
	public static final String user_team_team_type_02 = "02";
	/**
	 * 我的团队：三级
	 */
	public static final String user_team_team_type_03 = "03";
	/**
	 * 我的团队：总共
	 */
	public static final String user_team_team_type_04 = "04";
	

	/**
	 * 用户订单结算状态：待结算
	 */
	public static final String user_order_settle_status_00 = "00";
	/**
	 * 用户订单结算状态：结算中
	 */
	public static final String user_order_settle_status_02 = "02";
	/**
	 * 用户订单结算状态：结算成功
	 */
	public static final String user_order_settle_status_09 = "09";
	
	
	/**
	 * 支付宝交易类型：H5手机网站支付
	 */
	public static final String alipay_trade_type_01 = "01";
	/**
	 * 支付宝交易类型：APP支付
	 */
	public static final String alipay_trade_type_02 = "02";
	
	
	/**
	 * 微信交易类型：H5手机网站支付
	 */
	public static final String wechatpay_trade_type_01 = "01";
	/**
	 * 微信交易类型：APP支付
	 */
	public static final String wechatpay_trade_type_02 = "02";
	
	
	
	/**
	 * 延迟任务类型：超时订单自动关闭
	 */
	public static final String delay_order_oper_type_01 = "order_01";
	
	
	
	/**
	 * 延迟自营和供应链订单操作类型：超时未支付
	 */
	public static final String delay_user_order_oper_type_01 = "user_order_01";
	
	
	/**
	 * 用户异常操作类型：注册
	 */
	public static final String user_error_oper_log_error_type_01 = "01";
	
	
	/**
	 * 交易支付操作类型：余额充值
	 */
	public static final String user_trade_pay_oper_type_01 = "01";
	/**
	 * 交易支付操作类型：捐赠
	 */
	public static final String user_trade_pay_oper_type_02 = "02";
	
	
	/**
	 * 供应商状态：待开通
	 */
	public static final String supplier_info_supplier_status_00 = "00";
	/**
	 * 供应商状态：待审核
	 */
	public static final String supplier_info_supplier_status_04 = "04";
	/**
	 * 供应商状态：违规关闭
	 */
	public static final String supplier_info_supplier_status_06 = "06";
	/**
	 * 供应商状态：审核不通过
	 */
	public static final String supplier_info_supplier_status_08 = "08";
	/**
	 * 供应商状态：审核通过
	 */
	public static final String supplier_info_supplier_status_09 = "09";
	
	
	/**
	 * 系统钱包类型：积分（score_num）
	 */
	public static final String sys_purse_type_01 = "01";
	/**
	 * 系统钱包类型：i豆（ibean_num）
	 */
	public static final String sys_purse_type_02 = "02";
	/**
	 * 系统钱包类型：基金（fund_num）
	 */
	public static final String sys_purse_type_03 = "03";
	/**
	 * 系统钱包类型：自己业绩（self_performance）
	 */
	public static final String sys_purse_type_04 = "04";
	/**
	 * 系统钱包类型：直推业绩（refer_performance）
	 */
	public static final String sys_purse_type_05 = "05";
	/**
	 * 系统钱包类型：团队业绩（team_performance）
	 */
	public static final String sys_purse_type_06 = "06";
	
	
	/**
	 * 用户提现账户类型：余额
	 */
	public static final String user_cash_record_account_type_01 = "01";
	
	
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
	 * 一元抢购用户类型：自己
	 */
	public static final String user_goods_snapup_user_type_01 = "01";
	/**
	 * 一元抢购用户类型：其他人
	 */
	public static final String user_goods_snapup_user_type_02 = "02";
	
	
	/**
	 * 一元抢购中奖状态：待开奖
	 */
	public static final String user_goods_snapup_win_status_00 = "00";
	/**
	 * 一元抢购中奖状态：未中奖
	 */
	public static final String user_goods_snapup_win_status_08 = "08";
	/**
	 * 一元抢购中奖状态：已开奖
	 */
	public static final String user_goods_snapup_win_status_09 = "09";
	
	
	/**
	 * 一元抢购发货状态：待完善地址
	 */
	public static final String user_goods_snapup_send_status_00 = "00";
	/**
	 * 一元抢购发货状态：待发货
	 */
	public static final String user_goods_snapup_send_status_06 = "06";
	/**
	 * 一元抢购发货状态：已发货
	 */
	public static final String user_goods_snapup_send_status_09 = "09";
	
	
	/**
	 * 用户物流操作类型：商城下单
	 */
	public static final String user_order_shipper_oper_type_01 = "01";
	/**
	 * 用户物流操作类型：一元抢购
	 */
	public static final String user_order_shipper_oper_type_02 = "02";
	
	
	/**
	 * 用户互转类型：余额互转
	 */
	public static final String user_roll_log_type_01 = "01";
	/**
	 * 用户互转类型：积分互转
	 */
	public static final String user_roll_log_type_02 = "02";
	/**
	 * 用户互转类型：文票积分互转
	 */
	public static final String user_roll_log_type_03 = "03";
	
	
	/**
	 * 用户兑换类型：文票积分兑换
	 */
	public static final String user_exchange_log_type_01 = "01";
	
	
	/**
	 * 用户转账类型：转出
	 */
	public static final String user_roll_log_select_roll_type_01 = "01";
	/**
	 * 用户转账类型：转入
	 */
	public static final String user_roll_log_select_roll_type_02 = "02";
	

	/**
	 * 用户充值类型：线上
	 */
	public static final String user_recharge_line_type_01 = "01";
	/**
	 * 用户充值类型：线下
	 */
	public static final String user_recharge_line_type_02 = "02";
	
	
	/**
	 * 用户捐赠类型：线上
	 */
	public static final String user_donate_line_type_01 = "01";
	/**
	 * 用户捐赠类型：线下
	 */
	public static final String user_donate_line_type_02 = "02";
	
	
	/**
	 * 用户充值状态：充值中，待支付
	 */
	public static final String user_recharge_info_status_00 = "00";
	/**
	 * 用户余额充值状态：订单不存
	 */
	public static final String user_recharge_info_status_02 = "02";
	/**
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
	 * 用户线上充值类型：支付宝
	 */
	public static final String user_recharge_online_recharge_type_01 = "01";
	/**
	 * 用户线上充值类型：微信
	 */
	public static final String user_recharge_online_recharge_type_02 = "02";
	
	/**
	 * 用户线上充值渠道：川军
	 */
	public static final String user_recharge_online_channel_type_01 = "01";
	/**
	 * 用户线上充值渠道：金顺
	 */
	public static final String user_recharge_online_channel_type_02 = "02";
	/**
	 * 用户线上充值渠道：刺客
	 */
	public static final String user_recharge_online_channel_type_03 = "03";

	/**
	 * 用户线上充值渠道：狂神 支付宝扫码小额200-1000
	 */
	public static final String user_recharge_online_channel_type_04 = "04";

	/**
	 * 用户线上充值渠道：狂神 支付宝扫码大额800-5000
	 */
	public static final String user_recharge_online_channel_type_05 = "05";

	/**
	 * 用户线上充值渠道：狂神 支付宝扫码超大额2000-20000
	 */
	public static final String user_recharge_online_channel_type_06 = "06";
	
	/**
	 * 用户线上账户类型：余额
	 */
	public static final String user_recharge_online_account_type_01 = "01";
	
	
	/**
	 * 用户线上捐赠类型：支付宝
	 */
	public static final String user_donate_online_donate_type_01 = "01";
	/**
	 * 用户线上捐赠类型：微信
	 */
	public static final String user_donate_online_donate_type_02 = "02";
	
	
	/**
	 * 用户充值支付宝状态：支付中
	 */
	public static final String user_recharge_online_alipay_status_00 = "00";
	/**
	 * 用户充值支付宝状态：支付失败
	 */
	public static final String user_recharge_online_alipay_status_08 = "08";
	/**
	 * 用户充值支付宝状态：支付成功
	 */
	public static final String user_recharge_online_alipay_status_09 = "09";
	
	
	/**
	 * 用户等级：普通消费者
	 */
	public static final String user_info_grade_0 = "0";
	/**
	 * 用户等级：VIP
	 */
	public static final String user_info_grade_1 = "1";
	/**
	 * 用户等级：健康代言人
	 */
	public static final String user_info_grade_2 = "2";
	/**
	 * 用户等级：市级代理
	 */
	public static final String user_info_grade_3 = "3";
	/**
	 * 用户等级：集团发起人
	 */
	public static final String user_info_grade_4 = "4";
	

	/**
	 * 用户代理状态：申请中
	 */
	public static final String user_agent_status_04 = "04";
	/**
	 * 用户代理状态：申请失败
	 */
	public static final String user_agent_status_08 = "08";
	/**
	 * 用户代理状态：申请成功
	 */
	public static final String user_agent_status_09 = "09";
	
	
}
