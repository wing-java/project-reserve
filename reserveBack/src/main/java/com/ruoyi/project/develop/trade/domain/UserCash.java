package com.ruoyi.project.develop.trade.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_feedback
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserCash extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
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
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	/** 提现类型 */
	@Excel(name = "提现类型", readConverterExp = "01=支付宝,02=微信,03=银行卡")
	private String cash_type;
	/** 提现账户 */
	@Excel(name = "提现账户", readConverterExp = "01=积分")
	private String account_type;
	/** 提现金额 */
	@Excel(name = "提现金额")
	private String cash_money;
	/** 手续费金额 */
	@Excel(name = "手续费金额")
	private String feet_money;
	/** 到账金额 */
	@Excel(name = "到账金额")
	private String arrival_money;
	/** 手续费比例 */
	@Excel(name = "手续费比例")
	private String charge_rate;
	/** 最小手续费 */
	@Excel(name = "最小手续费")
	private String charge_min;
	/** 账号 */
	@Excel(name = "账号")
	private String account;
	/** 账户名 */
	@Excel(name = "账户名")
	private String account_name;
	/** 身份证号码 */
	@Excel(name = "身份证号码")
	private String legal_id_card;
	/** 证件照 */
	@Excel(name = "证件照")
	private String legal_crad_photo;
	/** 开户行 */
	@Excel(name = "开户行")
	private String bank_name;
	/** 开户行支行 */
	@Excel(name = "开户行支行")
	private String bank_branch_name;
	/** 账户收款码 */
	@Excel(name = "账户收款码")
	private String account_img;
	/** 状态 */
	@Excel(name = "状态", readConverterExp = "00=待处理,02=处理中,04=已撤销,08=处理失败,09=处理成功")
	private String cash_status;
	/** 操作备注 */
	@Excel(name = "操作备注")
	private String remark;
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	/** 更新人 */
	@Excel(name = "更新人")
	private String update_by;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCash_type() {
		return cash_type;
	}
	public void setCash_type(String cash_type) {
		this.cash_type = cash_type;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getCash_money() {
		return cash_money;
	}
	public void setCash_money(String cash_money) {
		this.cash_money = cash_money;
	}
	public String getFeet_money() {
		return feet_money;
	}
	public void setFeet_money(String feet_money) {
		this.feet_money = feet_money;
	}
	public String getArrival_money() {
		return arrival_money;
	}
	public void setArrival_money(String arrival_money) {
		this.arrival_money = arrival_money;
	}
	public String getCharge_rate() {
		return charge_rate;
	}
	public void setCharge_rate(String charge_rate) {
		this.charge_rate = charge_rate;
	}
	public String getCharge_min() {
		return charge_min;
	}
	public void setCharge_min(String charge_min) {
		this.charge_min = charge_min;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_branch_name() {
		return bank_branch_name;
	}
	public void setBank_branch_name(String bank_branch_name) {
		this.bank_branch_name = bank_branch_name;
	}
	public String getAccount_img() {
		return account_img;
	}
	public void setAccount_img(String account_img) {
		this.account_img = account_img;
	}
	public String getCash_status() {
		return cash_status;
	}
	public void setCash_status(String cash_status) {
		this.cash_status = cash_status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getLegal_id_card() {
		return legal_id_card;
	}
	public void setLegal_id_card(String legal_id_card) {
		this.legal_id_card = legal_id_card;
	}
	public String getLegal_crad_photo() {
		return legal_crad_photo;
	}
	public void setLegal_crad_photo(String legal_crad_photo) {
		this.legal_crad_photo = legal_crad_photo;
	}
	@Override
	public String toString() {
		return "UserCash [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account + ", uid=" + uid
				+ ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email=" + user_email
				+ ", nick_name=" + nick_name + ", order_id=" + order_id + ", cash_type=" + cash_type + ", account_type="
				+ account_type + ", cash_money=" + cash_money + ", feet_money=" + feet_money + ", arrival_money="
				+ arrival_money + ", charge_rate=" + charge_rate + ", charge_min=" + charge_min + ", account=" + account
				+ ", account_name=" + account_name + ", legal_id_card=" + legal_id_card + ", legal_crad_photo="
				+ legal_crad_photo + ", bank_name=" + bank_name + ", bank_branch_name=" + bank_branch_name
				+ ", account_img=" + account_img + ", cash_status=" + cash_status + ", remark=" + remark + ", cre_date="
				+ cre_date + ", up_date=" + up_date + ", update_by=" + update_by + "]";
	}
}
