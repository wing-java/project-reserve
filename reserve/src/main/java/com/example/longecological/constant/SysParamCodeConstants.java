package com.example.longecological.constant;

public class SysParamCodeConstants {

	/**
	 * 验证码每小时发送次数限制
	 */
	public static final String sys_param_code_verifySendHourLimit = "verifySendHourLimit";
	/**
	 * 验证码每天发送次数限制
	 */
	public static final String sys_param_code_verifySendDayLimit = "verifySendDayLimit";
	/**
	 * 验证码发送时长(单位/分)<发了之后过多久再次发>
	 */
	public static final String sys_param_code_verifySendDuration = "verifySendDuration";
	/**
	 * 验证码失效时长(单位/分)<发了之后不验证多久失效>
	 */
	public static final String sys_param_code_verifyInvalidDuration = "verifyInvalidDuration";
	
	/**
	 * 复购产品折扣
	 */
	public static final String sys_param_code_repurchaseProductDiscont = "repurchaseProductDiscont";
	/**
	 * 超时取消订单时间（单位：秒）
	 */
	public static final String sys_param_code_overTimeCancelOrder = "overTimeCancelOrder";
	
	/**
	 * WEB端注册链接
	 */
	public static final String sys_param_code_webRegisterLink = "webRegisterLink";
	
	/**
	 * 单个手机号注册人数限制
	 */
	public static final String sys_param_code_telRegisterNum = "telRegisterNum";
	/**
	 * 单个邮箱注册人数限制
	 */
	public static final String sys_param_code_emailRegisterNum = "emailRegisterNum";
	/**
	 * 余额互转最小数量
	 */
	public static final String sys_param_code_balanceRollMinNumber = "balanceRollMinNumber";
	/**
	 * 余额互转最低手续费
	 */
	public static final String sys_param_code_balanceRollMinCahrgeNum = "balanceRollMinCahrgeNum";
	/**
	 * 余额互转手续费率
	 */
	public static final String sys_param_code_balanceRollSingleCahrgeRate = "balanceRollSingleCahrgeRate";

	/**
	 * 用户最小提现金额
	 */
	public static final String sys_param_code_userBalanceMinCashMoney = "userBalanceMinCashMoney";
	/**
	 * 用户提现最低手续费
	 */
	public static final String sys_param_code_userBalanceCashMinCahrgeNum = "userBalanceCashMinCahrgeNum";
	/**
	 * 用户提现手续费率
	 */
	public static final String sys_param_code_userBalanceCashSingleCahrgeRate = "userBalanceCashSingleCahrgeRate";
	
	public static final String sys_param_code_unactivatedUserSigninNum = "unactivatedUserSigninNum";
	public static final String sys_param_code_activatedUserSigninNum = "activatedUserSigninNum";
	
	public static final String sys_param_code_userRegisterGiveMoney = "userRegisterGiveMoney";
	
	public static final String sys_param_code_cashDateRange = "cashDateRange";
	
}
