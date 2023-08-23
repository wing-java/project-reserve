package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 系统物流
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysCompany extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 公司名称 */
	@Excel(name = "公司名称")
	private String company_name;
	/** 公司logo */
	@Excel(name = "公司logo")
	private String company_logo;
	/** 公司规模 */
	@Excel(name = "公司规模")
	private String company_size;
	/** 公司地址 */
	@Excel(name = "公司地址")
	private String company_address;
	/** 公司简介 */
	@Excel(name = "公司简介")
	private String company_profile;
	/** 父级id */
	@Excel(name = "公司相册")
	private String company_photos;
	
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_logo() {
		return company_logo;
	}
	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}
	public String getCompany_size() {
		return company_size;
	}
	public void setCompany_size(String company_size) {
		this.company_size = company_size;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_profile() {
		return company_profile;
	}
	public void setCompany_profile(String company_profile) {
		this.company_profile = company_profile;
	}
	public String getCompany_photos() {
		return company_photos;
	}
	public void setCompany_photos(String company_photos) {
		this.company_photos = company_photos;
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
		return "SysCompany [id=" + id + ", company_name=" + company_name + ", company_logo=" + company_logo
				+ ", company_size=" + company_size + ", company_address=" + company_address + ", company_profile="
				+ company_profile + ", company_photos=" + company_photos + ", cre_date=" + cre_date + ", up_date="
				+ up_date + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}
}
