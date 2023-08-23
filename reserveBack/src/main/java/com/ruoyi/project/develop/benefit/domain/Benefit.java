package com.ruoyi.project.develop.benefit.domain;

import java.math.BigDecimal;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_benefit_record_diamond
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class Benefit extends BaseEntity
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
	
	/** 钱包类型 */
	@Excel(name = "钱包类型")
	private String purse_type;
	/** 流水类型编号 */
	@Excel(name = "流水类型编号")
	private String op_type;
	/** 流水类型名称 */
	@Excel(name = "流水类型名称")
	private String op_name;
	/** 订单号" */
	@Excel(name = "订单号")
	private String order_id;
	/** 变动前 */
	@Excel(name = "变动前")
	private BigDecimal before_num;
	/** 变动数量 */
	@Excel(name = "变动数量")
	private String num;
	/** 变动后 */
	@Excel(name = "变动后")
	private String after_num;
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
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
	public String getPurse_type() {
		return purse_type;
	}
	public void setPurse_type(String purse_type) {
		this.purse_type = purse_type;
	}
	public String getOp_type() {
		return op_type;
	}
	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public BigDecimal getBefore_num() {
		return before_num;
	}
	public void setBefore_num(BigDecimal before_num) {
		this.before_num = before_num;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getAfter_num() {
		return after_num;
	}
	public void setAfter_num(String after_num) {
		this.after_num = after_num;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	@Override
	public String toString() {
		return "BenefitBalance [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account + ", uid="
				+ uid + ", register_type=" + register_type + ", user_tel=" + user_tel + ", user_email=" + user_email
				+ ", nick_name=" + nick_name + ", purse_type=" + purse_type + ", op_type=" + op_type + ", op_name="
				+ op_name + ", order_id=" + order_id + ", before_num=" + before_num + ", num=" + num + ", after_num="
				+ after_num + ", cre_date=" + cre_date + "]";
	}
}
