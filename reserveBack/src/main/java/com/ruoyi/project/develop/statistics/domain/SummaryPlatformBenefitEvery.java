package com.ruoyi.project.develop.statistics.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_user_benefit_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SummaryPlatformBenefitEvery extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
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
	/** 创建时间 */
	@Excel(name = "统计日期")
	private String cre_date;
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
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	@Override
	public String toString() {
		return "SummaryPlatformBenefitEvery [balance_type_01=" + balance_type_01 + ", balance_type_02="
				+ balance_type_02 + ", balance_type_03=" + balance_type_03 + ", balance_type_04=" + balance_type_04
				+ ", balance_type_06=" + balance_type_06 + ", balance_type_07=" + balance_type_07 + ", balance_type_08="
				+ balance_type_08 + ", balance_type_10=" + balance_type_10 + ", balance_type_11=" + balance_type_11
				+ ", balance_type_12=" + balance_type_12 + ", balance_type_13=" + balance_type_13 + ", balance_type_14="
				+ balance_type_14 + ", balance_type_15=" + balance_type_15 + ", cre_date=" + cre_date + "]";
	}
}
