package com.ruoyi.project.develop.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserAccount extends BaseEntity
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
	/** 账号类型 */
	@Excel(name = "账号类型", readConverterExp = "01=支付宝,02=微信,03=银行卡")
	private String type;
	/** 收款码 */
	@Excel(name = "收款码")
	private String account_img;
	/** 是否默认 */
	@Excel(name = "是否默认", readConverterExp = "0=非默认,1=默认")
	private String isdefault;
	
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
	public String getLegal_id_card() {
		return legal_id_card;
	}
	public void setLegal_id_card(String legal_id_card) {
		this.legal_id_card = legal_id_card;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount_img() {
		return account_img;
	}
	public void setAccount_img(String account_img) {
		this.account_img = account_img;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
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
	public String getLegal_crad_photo() {
		return legal_crad_photo;
	}
	public void setLegal_crad_photo(String legal_crad_photo) {
		this.legal_crad_photo = legal_crad_photo;
	}
	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account + ", uid="
				+ uid + ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email=" + user_email
				+ ", nick_name=" + nick_name + ", account=" + account + ", account_name=" + account_name
				+ ", legal_id_card=" + legal_id_card + ", legal_crad_photo=" + legal_crad_photo + ", bank_name="
				+ bank_name + ", bank_branch_name=" + bank_branch_name + ", type=" + type + ", account_img="
				+ account_img + ", isdefault=" + isdefault + ", cre_date=" + cre_date + ", up_date=" + up_date + "]";
	}
}
