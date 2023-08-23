package com.ruoyi.project.develop.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_user_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class UserInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 代数 */
	@Excel(name = "代数")
	private String rank;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	/** UID */
	@Excel(name = "UID")
	private String uid;
	/** UID */
	@Excel(name = "邀请码")
	private String uid2;
	/** 账号 */
	@Excel(name = "账号")
	private String sys_user_account;
	/** 手机号 */
	@Excel(name = "手机号")
	private String user_tel;
	/** 邮箱 */
	@Excel(name = "邮箱")
	private String user_email;
	/** 昵称 */
	@Excel(name = "昵称 ")
	private String nick_name;
	/** 头像 */
	@Excel(name = "头像")
	private String head_photo;
	/** 用户注册类型 */
	@Excel(name = "用户注册类型", readConverterExp = "1=手机,2=邮箱")
	private String register_type;
	
	/** 状态  */
	@Excel(name = "状态", readConverterExp = "0=正常,1=黑名单")
	private String status;
	/** 状态  */
	@Excel(name = "实名状态", readConverterExp = "00=待提交,02=待审核,08=审核拒绝,09=审核通过")
	private String auth_status;
	
	/** 直推人数 */
	@Excel(name = "直推人数")
	private String referer_num;
	/** 团队人数 */
	@Excel(name = "团队人数")
	private String under_num;

	/** 余额数量 */
	@Excel(name = "余额数量")
	private String balance_num;
	/**  */
	@Excel(name = "股权数量")
	private String sharestock_num;
	/**  */
	@Excel(name = "总收益")
	private String total_benefit;
	/** 个人业绩 */
	@Excel(name = "认购金额")
	private String person_performance;
	
	/** 代数 */
	@Excel(name = "代数")
	private String algebra;
	/** 父级链 */
	@Excel(name = "父级链")
	private String parent_chain;

	/** 推荐人编号 */
	@Excel(name = "推荐人编号")
	private String referer_id;
	/** 推荐人注册类型 */
	@Excel(name = "推荐人注册类型", readConverterExp = "1=手机,2=邮箱")
	private String referer_register_type;
	/** 推荐人账号 */
	@Excel(name = "推荐人账号")
	private String referer_sys_user_account;
	/** 推荐人UID */
	@Excel(name = "推荐人UID")
	private String referer_uid;
	/** 推荐人邮箱 */
	@Excel(name = "推荐人邮箱")
	private String referer_user_email;
	/** 推荐人手机号 */
	@Excel(name = "推荐人手机号")
	private String referer_user_tel;
	/** 推荐人昵称 */
	@Excel(name = "推荐人昵称")
	private String referer_nick_name;
	/** 推荐人头像 */
	@Excel(name = "推荐人头像")
	private String referer_head_photo;
	
	/** 设备类型 */
	@Excel(name = "设备类型", type = Type.EXPORT)
	private String device_type;
	/** 设备号 */
	@Excel(name = "设备号", type = Type.EXPORT)
	private String device_no;
	/** 系统版本 */
	@Excel(name = "系统版本", type = Type.EXPORT)
	private String version_no;
	/** APPA版本 */
	@Excel(name = "APP版本", type = Type.EXPORT)
	private String app_version;
	
	/** 注册时间 */
	@Excel(name = "注册时间")
	private String cre_date;
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUid2() {
		return uid2;
	}
	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}
	public String getSys_user_account() {
		return sys_user_account;
	}
	public void setSys_user_account(String sys_user_account) {
		this.sys_user_account = sys_user_account;
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
	public String getHead_photo() {
		return head_photo;
	}
	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}
	public String getRegister_type() {
		return register_type;
	}
	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public String getReferer_num() {
		return referer_num;
	}
	public void setReferer_num(String referer_num) {
		this.referer_num = referer_num;
	}
	public String getUnder_num() {
		return under_num;
	}
	public void setUnder_num(String under_num) {
		this.under_num = under_num;
	}
	public String getBalance_num() {
		return balance_num;
	}
	public void setBalance_num(String balance_num) {
		this.balance_num = balance_num;
	}
	public String getSharestock_num() {
		return sharestock_num;
	}
	public void setSharestock_num(String sharestock_num) {
		this.sharestock_num = sharestock_num;
	}
	public String getTotal_benefit() {
		return total_benefit;
	}
	public void setTotal_benefit(String total_benefit) {
		this.total_benefit = total_benefit;
	}
	public String getPerson_performance() {
		return person_performance;
	}
	public void setPerson_performance(String person_performance) {
		this.person_performance = person_performance;
	}
	public String getAlgebra() {
		return algebra;
	}
	public void setAlgebra(String algebra) {
		this.algebra = algebra;
	}
	public String getParent_chain() {
		return parent_chain;
	}
	public void setParent_chain(String parent_chain) {
		this.parent_chain = parent_chain;
	}
	public String getReferer_id() {
		return referer_id;
	}
	public void setReferer_id(String referer_id) {
		this.referer_id = referer_id;
	}
	public String getReferer_register_type() {
		return referer_register_type;
	}
	public void setReferer_register_type(String referer_register_type) {
		this.referer_register_type = referer_register_type;
	}
	public String getReferer_sys_user_account() {
		return referer_sys_user_account;
	}
	public void setReferer_sys_user_account(String referer_sys_user_account) {
		this.referer_sys_user_account = referer_sys_user_account;
	}
	public String getReferer_uid() {
		return referer_uid;
	}
	public void setReferer_uid(String referer_uid) {
		this.referer_uid = referer_uid;
	}
	public String getReferer_user_email() {
		return referer_user_email;
	}
	public void setReferer_user_email(String referer_user_email) {
		this.referer_user_email = referer_user_email;
	}
	public String getReferer_user_tel() {
		return referer_user_tel;
	}
	public void setReferer_user_tel(String referer_user_tel) {
		this.referer_user_tel = referer_user_tel;
	}
	public String getReferer_nick_name() {
		return referer_nick_name;
	}
	public void setReferer_nick_name(String referer_nick_name) {
		this.referer_nick_name = referer_nick_name;
	}
	public String getReferer_head_photo() {
		return referer_head_photo;
	}
	public void setReferer_head_photo(String referer_head_photo) {
		this.referer_head_photo = referer_head_photo;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_no() {
		return device_no;
	}
	public void setDevice_no(String device_no) {
		this.device_no = device_no;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
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
	@Override
	public String toString() {
		return "UserInfo [rank=" + rank + ", id=" + id + ", uid=" + uid + ", uid2=" + uid2 + ", sys_user_account="
				+ sys_user_account + ", user_tel=" + user_tel + ", user_email=" + user_email + ", nick_name="
				+ nick_name + ", head_photo=" + head_photo + ", register_type=" + register_type + ", status=" + status
				+ ", auth_status=" + auth_status + ", referer_num=" + referer_num + ", under_num=" + under_num
				+ ", balance_num=" + balance_num + ", sharestock_num=" + sharestock_num + ", total_benefit="
				+ total_benefit + ", person_performance=" + person_performance + ", algebra=" + algebra
				+ ", parent_chain=" + parent_chain + ", referer_id=" + referer_id + ", referer_register_type="
				+ referer_register_type + ", referer_sys_user_account=" + referer_sys_user_account + ", referer_uid="
				+ referer_uid + ", referer_user_email=" + referer_user_email + ", referer_user_tel=" + referer_user_tel
				+ ", referer_nick_name=" + referer_nick_name + ", referer_head_photo=" + referer_head_photo
				+ ", device_type=" + device_type + ", device_no=" + device_no + ", version_no=" + version_no
				+ ", app_version=" + app_version + ", cre_date=" + cre_date + ", up_date=" + up_date + "]";
	}
	
}