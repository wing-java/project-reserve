package com.ruoyi.project.develop.email.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 验证码： t_verify_record
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class VerifyCode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 用户编号 */
	@Excel(name = "用户编号")
	private String user_id;
	
	/** 用户账号 */
	@Excel(name = "用户账号")
	private String user_name;
	
	/** 业务类型 */
	@Excel(name = "业务类型")
	private String bus_type;
	
	/** 接收类型 */
	@Excel(name = "接收类型")
	private String acc_type;

	/** 接收账号 */
	@Excel(name = "接收账号")
	private String account;
	
	/** 状态 */
	@Excel(name = "状态")
	private String status;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String create_time;
	
	/** 发送时间 */
	@Excel(name = "发送时间")
	private String send_time;
	
	/** 验证时间 */
	@Excel(name = "验证时间")
	private String verify_time;
	
	/** 模板编号/发送邮箱 */
	@Excel(name = "模板编号/发送邮箱")
	private String msg_template;

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(String verify_time) {
		this.verify_time = verify_time;
	}

	public String getMsg_template() {
		return msg_template;
	}

	public void setMsg_template(String msg_template) {
		this.msg_template = msg_template;
	}

	@Override
	public String toString() {
		return "VerifyCode [id=" + id + ", user_id=" + user_id + ", user_name=" + user_name + ", bus_type=" + bus_type
				+ ", acc_type=" + acc_type + ", account=" + account + ", status=" + status + ", create_time="
				+ create_time + ", send_time=" + send_time + ", verify_time=" + verify_time + ", msg_template="
				+ msg_template + "]";
	}
	
}
