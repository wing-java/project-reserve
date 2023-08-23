package com.ruoyi.project.develop.trade.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_feedback
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserRechargeOffline extends BaseEntity
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

	/** 充值渠道 */
	@Excel(name = "充值渠道", readConverterExp = "01=支付宝,02=微信,03=银行卡")
	private String recharge_type;
	
	/** 操作账户 */
	@Excel(name = "操作账户", readConverterExp = "01=余额")
	private String account_type;
	
	/** 充值数量 */
	@Excel(name = "充值数量")
	private String recharge_num;
	
	/** 充值凭证 */
	@Excel(name = "充值凭证")
	private String recharge_voucher;
	
	/** 充值状态 */
	@Excel(name = "充值状态", readConverterExp = "04=待审核,08=充值失败,09=充值成功")
	private String status;
	
	/** 审核备注 */
	@Excel(name = "审核备注")
	private String note;
	
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

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRecharge_type() {
		return recharge_type;
	}

	public void setRecharge_type(String recharge_type) {
		this.recharge_type = recharge_type;
	}

	public String getRecharge_num() {
		return recharge_num;
	}

	public void setRecharge_num(String recharge_num) {
		this.recharge_num = recharge_num;
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

	public String getRecharge_voucher() {
		return recharge_voucher;
	}

	public void setRecharge_voucher(String recharge_voucher) {
		this.recharge_voucher = recharge_voucher;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	@Override
	public String toString() {
		return "UserRechargeOffline [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account
				+ ", uid=" + uid + ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email="
				+ user_email + ", nick_name=" + nick_name + ", order_id=" + order_id + ", recharge_type="
				+ recharge_type + ", account_type=" + account_type + ", recharge_num=" + recharge_num
				+ ", recharge_voucher=" + recharge_voucher + ", status=" + status + ", note=" + note + ", remark="
				+ remark + ", cre_date=" + cre_date + ", up_date=" + up_date + ", update_by=" + update_by + "]";
	}

}
