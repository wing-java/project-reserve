package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * APP图片： t_sys_app_img
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysBenefitName extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	@Excel(name = "编号")
	private Integer id;
	
	/** 图片类型 */
	@Excel(name = "操作类型")
	private String op_type;
	
	/** 图片链接 */
	@Excel(name = "操作名称")
	private String op_name;
	
	/** 备注说明 */
	@Excel(name = "变化类型", readConverterExp = "01=SPN钱包,02=报单钱包,03=奖励钱包,04=定存钱包")
	private String change_type;
	
	/** 创建时间 */
	@Excel(name = "创建时间")
	private String cre_date;
	
	/** 更新时间 */
	@Excel(name = "更新时间")
	private String up_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public String getChange_type() {
		return change_type;
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
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
		return "SysBenefitName [id=" + id + ", op_type=" + op_type + ", op_name=" + op_name + ", change_type="
				+ change_type + ", cre_date=" + cre_date + ", up_date=" + up_date + "]";
	}
}
