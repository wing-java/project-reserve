package com.ruoyi.project.develop.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_login_log
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserLoginLog extends BaseEntity
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
	
	/** 邀请码 */
	@Excel(name = "邀请码")
	private String invite_code;
	
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
	
	/** 登录ip地址 */
	@Excel(name = "登录ip地址")
	private String ipaddr;
	
	/** 登录地点 */
	@Excel(name = "登录地点")
	private String login_location;
	
	/** 浏览器类型 */
	@Excel(name = "浏览器类型")
	private String browser;
	
	/** 操作系统 */
	@Excel(name = "操作系统")
	private String os;
	
	/** 登录时间 */
	@Excel(name = "登录时间")
	private String login_time;

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

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
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

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getLogin_location() {
		return login_location;
	}

	public void setLogin_location(String login_location) {
		this.login_location = login_location;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	@Override
	public String toString() {
		return "UserLoginLog [id=" + id + ", user_id=" + user_id + ", sys_user_account=" + sys_user_account
				+ ", invite_code=" + invite_code + ", register_type=" + register_type + ", user_tel=" + user_tel
				+ ", user_email=" + user_email + ", nick_name=" + nick_name + ", ipaddr=" + ipaddr + ", login_location="
				+ login_location + ", browser=" + browser + ", os=" + os + ", login_time=" + login_time + "]";
	}
	
}
