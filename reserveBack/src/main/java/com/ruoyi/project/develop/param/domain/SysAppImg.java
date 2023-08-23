package com.ruoyi.project.develop.param.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * APP图片： t_sys_app_img
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SysAppImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	@Excel(name = "编号")
	private Integer id;
	
	/** 图片类型 */
	@Excel(name = "图片类型", readConverterExp = "01=首页轮播图")
	private String img_type;
	
	/** 图片路径 */
	@Excel(name = "图片路径")
	private String img_url;
	
	/** 图片链接 */
	@Excel(name = "图片链接")
	private String img_link;
	
	/** 备注说明 */
	@Excel(name = "备注说明")
	private String note;
	
	/** 排序 */
	@Excel(name = "排序")
	private String order_num;

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

	public String getImg_type() {
		return img_type;
	}

	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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

	public String getImg_link() {
		return img_link;
	}

	public void setImg_link(String img_link) {
		this.img_link = img_link;
	}

	@Override
	public String toString() {
		return "SysAppImg [id=" + id + ", img_type=" + img_type + ", img_url=" + img_url + ", img_link=" + img_link
				+ ", note=" + note + ", order_num=" + order_num + ", cre_date=" + cre_date + ", up_date=" + up_date
				+ ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}

}
