package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_sys_param
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysParam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 参数编号 */
	@Excel(name = "参数编号")
	private Integer id;
	
	/** 参数代码 */
	@Excel(name = "参数代码")
	private String code;
	
	/** 参数系统 */
	@Excel(name = "参数系统")
	private String system;
	
	/** 参数备注 */
	@Excel(name = "参数备注")
	private String note;
	
	/** 操作备注 */
	@Excel(name = "操作备注 ")
	private String remark;
	
	/** 参数值 */
	@Excel(name = "参数值 ")
	private String value;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		return "SysParam [id=" + id + ", code=" + code + ", system=" + system + ", note=" + note + ", remark=" + remark
				+ ", value=" + value + ", cre_date=" + cre_date + ", up_date=" + up_date + ", create_by=" + create_by
				+ ", update_by=" + update_by + "]";
	}
}
