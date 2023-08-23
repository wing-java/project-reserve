package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_sys_version_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysVersion extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 版本号 */
	@Excel(name = "版本号")
	private String version_no;
	
	/** 版本下载路径 */
	@Excel(name = "版本下载路径")
	private String version_url;
	
	/** 是否强制更新  */
	@Excel(name = "是否强制更新 ", readConverterExp = "0=否,1=是")
	private String status;

	/** 设备类型 */
	@Excel(name = "设备类型")
	private String device_type;
	
	/** 更新备注  */
	@Excel(name = "更新备注")
	private String note;
	
	/** 操作备注 */
	@Excel(name = "操作备注")
	private String remark;
	
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

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}

	public String getVersion_url() {
		return version_url;
	}

	public void setVersion_url(String version_url) {
		this.version_url = version_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
		return "SysVersion [id=" + id + ", version_no=" + version_no + ", version_url=" + version_url + ", status="
				+ status + ", device_type=" + device_type + ", note=" + note + ", remark=" + remark + ", cre_date="
				+ cre_date + ", up_date=" + up_date + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}

}
