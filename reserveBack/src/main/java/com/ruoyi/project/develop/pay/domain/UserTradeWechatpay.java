package com.ruoyi.project.develop.pay.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_deposit_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserTradeWechatpay extends BaseEntity
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
	
	/** 操作类型 */
	@Excel(name = "操作类型", readConverterExp = "01=余额充值,02=用户捐赠")
	private String oper_type;
	
	/** appid */
	@Excel(name = "appid")
	private String appid;
	
	/** 微信支付分配的商户号 */
	@Excel(name = "微信支付分配的商户号")
	private String mch_id;
	
	/** 交易类型 */
	@Excel(name = "交易类型")
	private String trade_type;
	
	/** 微信支付订单号 */
	@Excel(name = "微信支付订单号")
	private String transaction_id;
	
	/** 随机字符串 */
	@Excel(name = "随机字符串")
	private String nonce_str;

	/** 支付状态 */
	@Excel(name = "支付状态", readConverterExp = "00=支付中,08-支付失败,09=支付成功")
	private String status;
	
	/** 业务结果 */
	@Excel(name = "业务结果")
	private String result_code;
	
	/** 错误代码 */
	@Excel(name = "错误代码")
	private String err_code;
	
	/** 错误代码描述 */
	@Excel(name = "错误代码描述")
	private String err_code_des;
	
	/** 付款银行 */
	@Excel(name = "付款银行")
	private String bank_type;
	
	/** 用户标识 */
	@Excel(name = "用户标识")
	private String openid;
	
	/** 货币种类 */
	@Excel(name = "货币种类")
	private String fee_type;
	
	/** 现金支付金额 */
	@Excel(name = "现金支付金额")
	private String cash_fee;
	
	/** 订单金额 */
	@Excel(name = "订单金额")
	private String total_fee;
	
	/** 支付完成时间 */
	@Excel(name = "支付完成时间")
	private String time_end;
	
	/** 是否关注公众账号 */
	@Excel(name = "是否关注公众账号")
	private String is_subscribe;
	
	/** 返回状态码 */
	@Excel(name = "返回状态码")
	private String return_code;
	
	/** 商品描述 */
	@Excel(name = "商品描述")
	private String body;
	
	/** 终端IP */
	@Excel(name = "终端IP")
	private String spbill_create_ip;
	
	/** 预支付交易会话标识 */
	@Excel(name = "预支付交易会话标识")
	private String prepay_id;
	
	/** 调微信支付接口地址 */
	@Excel(name = "调微信支付接口地址")
	private String mweb_url;
	
	/** 交易状态 */
	@Excel(name = "交易状态")
	private String trade_state;
	
	/** 返回码消息 */
	@Excel(name = "返回码消息")
	private String return_msg;
	
	/** cash_fee_type */
	@Excel(name = "cash_fee_type")
	private String cash_fee_type;
	
	/** 交易状态描述 */
	@Excel(name = "交易状态描述")
	private String trade_state_desc;
	
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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getMweb_url() {
		return mweb_url;
	}

	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
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
		return "UserTradeWechatpay [id=" + id + ", out_trade_no=" + out_trade_no + ", user_id=" + user_id
				+ ", sys_user_account=" + sys_user_account + ", uid=" + uid + ", register_type=" + register_type
				+ ", user_tel=" + user_tel + ", user_email=" + user_email + ", nick_name=" + nick_name + ", oper_type="
				+ oper_type + ", appid=" + appid + ", mch_id=" + mch_id + ", trade_type=" + trade_type
				+ ", transaction_id=" + transaction_id + ", nonce_str=" + nonce_str + ", status=" + status
				+ ", result_code=" + result_code + ", err_code=" + err_code + ", err_code_des=" + err_code_des
				+ ", bank_type=" + bank_type + ", openid=" + openid + ", fee_type=" + fee_type + ", cash_fee="
				+ cash_fee + ", total_fee=" + total_fee + ", time_end=" + time_end + ", is_subscribe=" + is_subscribe
				+ ", return_code=" + return_code + ", body=" + body + ", spbill_create_ip=" + spbill_create_ip
				+ ", prepay_id=" + prepay_id + ", mweb_url=" + mweb_url + ", trade_state=" + trade_state
				+ ", return_msg=" + return_msg + ", cash_fee_type=" + cash_fee_type + ", trade_state_desc="
				+ trade_state_desc + ", cre_date=" + cre_date + ", up_date=" + up_date + "]";
	}

}
