package com.ruoyi.project.develop.email.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * APP图片： t_sys_email_account
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysEmailAccount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 邮箱编号 */
	@Excel(name = "邮箱编号")
	private String num;
	
	/** 邮箱账号 */
	@Excel(name = "邮箱账号")
	private String account;
	
	/** 邮箱授权码 */
	@Excel(name = "邮箱授权码")
	private String password;
	
	/** 登录密码 */
	@Excel(name = "登录密码")
	private String login_pass;

	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	
	/** 创建者 */
	@Excel(name = "创建者")
	private String create_by;
	
	/** 更新者 */
	@Excel(name = "更新者")
	private String update_by;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_pass() {
		return login_pass;
	}

	public void setLogin_pass(String login_pass) {
		this.login_pass = login_pass;
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

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	@Override
	public String toString() {
		return "SysEmailAccount [id=" + id + ", num=" + num + ", account=" + account + ", password=" + password
				+ ", login_pass=" + login_pass + ", cre_date=" + cre_date + ", up_date=" + up_date + ", create_by="
				+ create_by + ", update_by=" + update_by + "]";
	}
	
}
