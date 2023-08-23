package com.ruoyi.project.develop.sysoper.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_sys_recycle
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserSysRecycle extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 订单号 */
	@Excel(name = "订单号")
	private String order_id;
	
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
	@Excel(name = "钱包类型", readConverterExp = "01=余额")
	private String purse_type;

	/** 拨款数量 */
	@Excel(name = "拨款数量")
	private String num;
	
	/** 操作备注 */
	@Excel(name = "操作备注")
	private String remark;
	
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

	public String getPurse_type() {
		return purse_type;
	}

	public void setPurse_type(String purse_type) {
		this.purse_type = purse_type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	
}
