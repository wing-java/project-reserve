package com.ruoyi.project.develop.statistics.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_user_benefit_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SummaryUserBenefitAll extends BaseEntity
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
	
	/** 余额充值（balance_num） */
	@Excel(name = " 余额充值（balance_num）")
	private String balance_type_01;
	/** 购买产品（balance_num） */
	@Excel(name = "购买产品（balance_num）")
	private String balance_type_02;
	/** 公司拨款（balance_num）  */
	@Excel(name = "公司拨款（balance_num）")
	private String balance_type_03;
	/** 公司扣款（balance_num） */
	@Excel(name = "公司扣款（balance_num）")
	private String balance_type_04;
	/** 余额互转（balance_num） */
	@Excel(name = "余额互转（balance_num）")
	private String balance_type_06;
	/** 申请提现（balance_num） */
	@Excel(name = "申请提现（balance_num）")
	private String balance_type_07;
	/** 提现失败（balance_num） */
	@Excel(name = "提现失败（balance_num）")
	private String balance_type_08;
	/** 直推奖励（balance_num） */
	@Excel(name = "直推奖励（balance_num）")
	private String balance_type_10;
	/** 市代理区域奖励（balance_num） */
	@Excel(name = "市代理区域奖励（balance_num）")
	private String balance_type_11;
	/** 市代言人分红奖励（balance_num） */
	@Excel(name = "市代言人分红奖励（balance_num）")
	private String balance_type_12;
	/** 全国代言人分红奖励（balance_num） */
	@Excel(name = "全国代言人分红奖励（balance_num）")
	private String balance_type_13;
	/** 集团分红奖励（balance_num） */
	@Excel(name = "集团分红奖励（balance_num）")
	private String balance_type_14;
	/** 集团加权分红奖励（balance_num） */
	@Excel(name = "集团加权分红奖励（balance_num）")
	private String balance_type_15;
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
	public String getBalance_type_01() {
		return balance_type_01;
	}
	public void setBalance_type_01(String balance_type_01) {
		this.balance_type_01 = balance_type_01;
	}
	public String getBalance_type_02() {
		return balance_type_02;
	}
	public void setBalance_type_02(String balance_type_02) {
		this.balance_type_02 = balance_type_02;
	}
	public String getBalance_type_03() {
		return balance_type_03;
	}
	public void setBalance_type_03(String balance_type_03) {
		this.balance_type_03 = balance_type_03;
	}
	public String getBalance_type_04() {
		return balance_type_04;
	}
	public void setBalance_type_04(String balance_type_04) {
		this.balance_type_04 = balance_type_04;
	}
	public String getBalance_type_06() {
		return balance_type_06;
	}
	public void setBalance_type_06(String balance_type_06) {
		this.balance_type_06 = balance_type_06;
	}
	public String getBalance_type_07() {
		return balance_type_07;
	}
	public void setBalance_type_07(String balance_type_07) {
		this.balance_type_07 = balance_type_07;
	}
	public String getBalance_type_08() {
		return balance_type_08;
	}
	public void setBalance_type_08(String balance_type_08) {
		this.balance_type_08 = balance_type_08;
	}
	public String getBalance_type_10() {
		return balance_type_10;
	}
	public void setBalance_type_10(String balance_type_10) {
		this.balance_type_10 = balance_type_10;
	}
	public String getBalance_type_11() {
		return balance_type_11;
	}
	public void setBalance_type_11(String balance_type_11) {
		this.balance_type_11 = balance_type_11;
	}
	public String getBalance_type_12() {
		return balance_type_12;
	}
	public void setBalance_type_12(String balance_type_12) {
		this.balance_type_12 = balance_type_12;
	}
	public String getBalance_type_13() {
		return balance_type_13;
	}
	public void setBalance_type_13(String balance_type_13) {
		this.balance_type_13 = balance_type_13;
	}
	public String getBalance_type_14() {
		return balance_type_14;
	}
	public void setBalance_type_14(String balance_type_14) {
		this.balance_type_14 = balance_type_14;
	}
	public String getBalance_type_15() {
		return balance_type_15;
	}
	public void setBalance_type_15(String balance_type_15) {
		this.balance_type_15 = balance_type_15;
	}
	@Override
	public String toString() {
		return "SummaryUserBenefitAll [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account
				+ ", uid=" + uid + ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email="
				+ user_email + ", nick_name=" + nick_name + ", balance_type_01=" + balance_type_01
				+ ", balance_type_02=" + balance_type_02 + ", balance_type_03=" + balance_type_03 + ", balance_type_04="
				+ balance_type_04 + ", balance_type_06=" + balance_type_06 + ", balance_type_07=" + balance_type_07
				+ ", balance_type_08=" + balance_type_08 + ", balance_type_10=" + balance_type_10 + ", balance_type_11="
				+ balance_type_11 + ", balance_type_12=" + balance_type_12 + ", balance_type_13=" + balance_type_13
				+ ", balance_type_14=" + balance_type_14 + ", balance_type_15=" + balance_type_15 + "]";
	}
}
