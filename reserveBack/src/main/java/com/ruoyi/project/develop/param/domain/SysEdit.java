package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_sys_edit_info
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysEdit extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	@Excel(name = "编号")
	private Integer id;
	
	/** 表名 */
	@Excel(name = "表名")
	private String table_name;
	
	/** 旧值 */
	@Excel(name = "旧值")
	private String old_value;
	
	/** 新值 */
	@Excel(name = "新值 ")
	private String new_value;
	
	/** 备注 */
	@Excel(name = "备注 ")
	private String remark;

	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 创建者 */
	@Excel(name = "创建者")
	private String create_by;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getOld_value() {
		return old_value;
	}

	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}

	public String getNew_value() {
		return new_value;
	}

	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysEdit [id=" + id + ", table_name=" + table_name + ", old_value=" + old_value + ", new_value="
				+ new_value + ", remark=" + remark + ", cre_date=" + cre_date + ", create_by=" + create_by + "]";
	}

}
