package com.ruoyi.project.develop.pay.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_deposit_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserTradeAlipay extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 商户订单号 */
	@Excel(name = "商户订单号")
	private String out_trade_no;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 账号 */
	@Excel(name = "账号")
	private String sys_user_account;
	
	/** UID */
	@Excel(name = "UID")
	private String uid;
	
	/** 用户注册类型 */
	@Excel(name = "用户注册类型", readConverterExp = "1=手机,2=邮箱")
	private String register_type;
	
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	
	/** 邮箱 */
	@Excel(name = "邮箱")
	private String user_email;
	
	/** 昵称 */
	@Excel(name = "昵称 ")
	private String nick_name;
	
	/** app_id */
	@Excel(name = "app_id")
	private String app_id;
	
	/** 交易状态 */
	@Excel(name = "交易状态")
	private String trade_status;
	
	/** 交易号 */
	@Excel(name = "交易号")
	private String trade_no;
	
	/** 交易金额 */
	@Excel(name = "交易金额")
	private String total_amount;
	
	/** 操作类型 */
	@Excel(name = "操作类型", readConverterExp = "01=余额充值,02=用户捐赠")
	private String oper_type;
	
	/** 主题 */
	@Excel(name = "主题")
	private String subject;
	
	/** 描述 */
	@Excel(name = "描述")
	private String body;
	
	/** 支付状态 */
	@Excel(name = "支付状态", readConverterExp = "00=支付中,08-支付失败,09=支付成功")
	private String status;
	
	/** 商品费用 */
	@Excel(name = "最晚付款时间")
	private String timeout_express;
	
	/** seller_email */
	@Excel(name = "seller_email")
	private String seller_email;
	
	/** 买家支付宝用户号 */
	@Excel(name = "买家支付宝用户号")
	private String buyer_id;
	
	/** 开票金额 */
	@Excel(name = "开票金额")
	private String invoice_amount;
	
	/** 支付金额信息 */
	@Excel(name = "支付金额信息")
	private String fund_bill_list;
	
	/** 通知类型 */
	@Excel(name = "通知类型")
	private String notify_type;
	
	/** 实收金额 */
	@Excel(name = "实收金额")
	private String receipt_amount;
	
	/** 付款金额 */
	@Excel(name = "付款金额")
	private String buyer_pay_amount;
	
	/** 卖家支付宝用户号 */
	@Excel(name = "卖家支付宝用户号")
	private String seller_id;
	
	/** 交易付款时间 */
	@Excel(name = "交易付款时间")
	private String gmt_payment;
	
	/** 通知时间 */
	@Excel(name = "通知时间")
	private String notify_time;
	
	/** 付款appid */
	@Excel(name = "付款appid")
	private String auth_app_id;
	
	/** 买家支付宝账号 */
	@Excel(name = "买家支付宝账号")
	private String buyer_logon_id;
	
	/** 集分宝金额 */
	@Excel(name = "集分宝金额")
	private String point_amount;
	
	/** 业务返回码描述 */
	@Excel(name = "业务返回码描述")
	private String sub_msg;
	
	/** 业务返回码 */
	@Excel(name = "业务返回码")
	private String sub_code;
	
	/** 支付有效时长 */
	@Excel(name = "支付有效时长")
	private String timeout_experss;
	
	/** 本次交易打款给卖家的时间 */
	@Excel(name = "本次交易打款给卖家的时间")
	private String send_pay_date;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSys_user_account() {
		return sys_user_account;
	}

	public void setSys_user_account(String sys_user_account) {
		this.sys_user_account = sys_user_account;
	}

	public String getRegister_type() {
		return register_type;
	}

	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(String invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public String getFund_bill_list() {
		return fund_bill_list;
	}

	public void setFund_bill_list(String fund_bill_list) {
		this.fund_bill_list = fund_bill_list;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getReceipt_amount() {
		return receipt_amount;
	}

	public void setReceipt_amount(String receipt_amount) {
		this.receipt_amount = receipt_amount;
	}

	public String getBuyer_pay_amount() {
		return buyer_pay_amount;
	}

	public void setBuyer_pay_amount(String buyer_pay_amount) {
		this.buyer_pay_amount = buyer_pay_amount;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getAuth_app_id() {
		return auth_app_id;
	}

	public void setAuth_app_id(String auth_app_id) {
		this.auth_app_id = auth_app_id;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public String getPoint_amount() {
		return point_amount;
	}

	public void setPoint_amount(String point_amount) {
		this.point_amount = point_amount;
	}

	public String getSub_msg() {
		return sub_msg;
	}

	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}

	public String getSub_code() {
		return sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getTimeout_experss() {
		return timeout_experss;
	}

	public void setTimeout_experss(String timeout_experss) {
		this.timeout_experss = timeout_experss;
	}

	public String getSend_pay_date() {
		return send_pay_date;
	}

	public void setSend_pay_date(String send_pay_date) {
		this.send_pay_date = send_pay_date;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	@Override
	public String toString() {
		return "UserTradeAlipay [id=" + id + ", out_trade_no=" + out_trade_no + ", user_id=" + user_id
				+ ", sys_user_account=" + sys_user_account + ", uid=" + uid + ", register_type=" + register_type
				+ ", user_tel=" + user_tel + ", user_email=" + user_email + ", nick_name=" + nick_name + ", app_id="
				+ app_id + ", trade_status=" + trade_status + ", trade_no=" + trade_no + ", total_amount="
				+ total_amount + ", oper_type=" + oper_type + ", subject=" + subject + ", body=" + body + ", status="
				+ status + ", timeout_express=" + timeout_express + ", seller_email=" + seller_email + ", buyer_id="
				+ buyer_id + ", invoice_amount=" + invoice_amount + ", fund_bill_list=" + fund_bill_list
				+ ", notify_type=" + notify_type + ", receipt_amount=" + receipt_amount + ", buyer_pay_amount="
				+ buyer_pay_amount + ", seller_id=" + seller_id + ", gmt_payment=" + gmt_payment + ", notify_time="
				+ notify_time + ", auth_app_id=" + auth_app_id + ", buyer_logon_id=" + buyer_logon_id
				+ ", point_amount=" + point_amount + ", sub_msg=" + sub_msg + ", sub_code=" + sub_code
				+ ", timeout_experss=" + timeout_experss + ", send_pay_date=" + send_pay_date + ", cre_date=" + cre_date
				+ ", up_date=" + up_date + "]";
	}

}
