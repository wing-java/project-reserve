package com.ruoyi.project.develop.trade.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_roll_log
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserRoll extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	/** 转账类型 */
	@Excel(name = "转账类型", readConverterExp = "01=余额互转")
	private String roll_type;
	
	/** 用户编号 */
	@Excel(name = "转出用户编号")
	private String out_user_id;
	/** 用户账号 */
	@Excel(name = "转出用户账号")
	private String out_sys_user_account;
	/** 用户邀请码 */
	@Excel(name = "转出用户UID")
	private String out_uid;
	/** 用户注册类型 */
	@Excel(name = "转出用户注册类型", readConverterExp = "1=手机,2=邮箱")
	private String out_register_type;
	/** 用户手机号 */
	@Excel(name = "转出用户手机号")
	private String out_user_tel;
	/** 用户邮箱 */
	@Excel(name = "转出用户邮箱")
	private String out_user_email;
	/** 用户昵称 */
	@Excel(name = "转出用户昵称")
	private String out_nick_name;
	
	/** 用户编号 */
	@Excel(name = "转入用户编号")
	private String in_user_id;
	/** 用户账号 */
	@Excel(name = "转入用户账号")
	private String in_sys_user_account;
	/** 用户邀请码 */
	@Excel(name = "转入用户UID")
	private String in_uid;
	/** 用户注册类型 */
	@Excel(name = "转入用户注册类型", readConverterExp = "1=手机,2=邮箱")
	private String in_register_type;
	/** 用户手机号 */
	@Excel(name = "转入用户手机号")
	private String in_user_tel;
	/** 用户邮箱 */
	@Excel(name = "转入用户邮箱")
	private String in_user_email;
	/** 用户昵称 */
	@Excel(name = "转入用户昵称")
	private String in_nick_name;
	
	/** 转出数量 */
	@Excel(name = "转出数量")
	private String roll_num;
	/** 转出手续费 */
	@Excel(name = "转出手续费")
	private String roll_charge;
	/** 手续费比例 */
	@Excel(name = "手续费比例")
	private String charge_rate;
	/** 手续费比例 */
	@Excel(name = "最小手续费")
	private String charge_min;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getRoll_type() {
		return roll_type;
	}

	public void setRoll_type(String roll_type) {
		this.roll_type = roll_type;
	}

	public String getOut_user_id() {
		return out_user_id;
	}

	public void setOut_user_id(String out_user_id) {
		this.out_user_id = out_user_id;
	}

	public String getOut_register_type() {
		return out_register_type;
	}

	public void setOut_register_type(String out_register_type) {
		this.out_register_type = out_register_type;
	}

	public String getOut_user_tel() {
		return out_user_tel;
	}

	public void setOut_user_tel(String out_user_tel) {
		this.out_user_tel = out_user_tel;
	}

	public String getOut_user_email() {
		return out_user_email;
	}

	public void setOut_user_email(String out_user_email) {
		this.out_user_email = out_user_email;
	}

	public String getOut_nick_name() {
		return out_nick_name;
	}

	public void setOut_nick_name(String out_nick_name) {
		this.out_nick_name = out_nick_name;
	}

	public String getIn_user_id() {
		return in_user_id;
	}

	public void setIn_user_id(String in_user_id) {
		this.in_user_id = in_user_id;
	}

	public String getIn_register_type() {
		return in_register_type;
	}

	public void setIn_register_type(String in_register_type) {
		this.in_register_type = in_register_type;
	}

	public String getIn_user_tel() {
		return in_user_tel;
	}

	public void setIn_user_tel(String in_user_tel) {
		this.in_user_tel = in_user_tel;
	}

	public String getIn_user_email() {
		return in_user_email;
	}

	public void setIn_user_email(String in_user_email) {
		this.in_user_email = in_user_email;
	}

	public String getIn_nick_name() {
		return in_nick_name;
	}

	public void setIn_nick_name(String in_nick_name) {
		this.in_nick_name = in_nick_name;
	}

	public String getRoll_num() {
		return roll_num;
	}

	public void setRoll_num(String roll_num) {
		this.roll_num = roll_num;
	}

	public String getRoll_charge() {
		return roll_charge;
	}

	public void setRoll_charge(String roll_charge) {
		this.roll_charge = roll_charge;
	}

	public String getCharge_rate() {
		return charge_rate;
	}

	public void setCharge_rate(String charge_rate) {
		this.charge_rate = charge_rate;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getOut_sys_user_account() {
		return out_sys_user_account;
	}

	public void setOut_sys_user_account(String out_sys_user_account) {
		this.out_sys_user_account = out_sys_user_account;
	}

	public String getIn_sys_user_account() {
		return in_sys_user_account;
	}

	public void setIn_sys_user_account(String in_sys_user_account) {
		this.in_sys_user_account = in_sys_user_account;
	}

	public String getCharge_min() {
		return charge_min;
	}

	public void setCharge_min(String charge_min) {
		this.charge_min = charge_min;
	}

	public String getOut_uid() {
		return out_uid;
	}

	public String getIn_uid() {
		return in_uid;
	}

	public void setOut_uid(String out_uid) {
		this.out_uid = out_uid;
	}

	public void setIn_uid(String in_uid) {
		this.in_uid = in_uid;
	}

	@Override
	public String toString() {
		return "UserRoll [id=" + id + ", order_id=" + order_id + ", roll_type=" + roll_type + ", out_user_id="
				+ out_user_id + ", out_sys_user_account=" + out_sys_user_account + ", out_uid=" + out_uid
				+ ", out_register_type=" + out_register_type + ", out_user_tel=" + out_user_tel + ", out_user_email="
				+ out_user_email + ", out_nick_name=" + out_nick_name + ", in_user_id=" + in_user_id
				+ ", in_sys_user_account=" + in_sys_user_account + ", in_uid=" + in_uid + ", in_register_type="
				+ in_register_type + ", in_user_tel=" + in_user_tel + ", in_user_email=" + in_user_email
				+ ", in_nick_name=" + in_nick_name + ", roll_num=" + roll_num + ", roll_charge=" + roll_charge
				+ ", charge_rate=" + charge_rate + ", charge_min=" + charge_min + ", cre_date=" + cre_date + "]";
	}

}
