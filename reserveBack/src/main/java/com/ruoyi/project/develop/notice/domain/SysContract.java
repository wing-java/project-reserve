package com.ruoyi.project.develop.notice.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 合同资讯表 sys_notice
 * 
 * @author ruoyi
 */
public class SysContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private String id;
    /** 合同类型 */
    private String contract_type;
    /** 合同标题 */
    private String contract_title;
    /** 合同内容 */
    private String news_content;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    private String cre_date;
    /** 更新时间 */
    private String up_date;
    /** 创建人 */
    private String create_by;
    /** 更新人 */
    private String update_by;
	public String getId() {
		return id;
	}
	public String getContract_type() {
		return contract_type;
	}
	public String getContract_title() {
		return contract_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public String getRemark() {
		return remark;
	}
	public String getCre_date() {
		return cre_date;
	}
	public String getUp_date() {
		return up_date;
	}
	public String getCreate_by() {
		return create_by;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}
	public void setContract_title(String contract_title) {
		this.contract_title = contract_title;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	@Override
	public String toString() {
		return "SysContract [id=" + id + ", contract_type=" + contract_type + ", contract_title=" + contract_title
				+ ", news_content=" + news_content + ", remark=" + remark + ", cre_date=" + cre_date + ", up_date="
				+ up_date + ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}
	
}
