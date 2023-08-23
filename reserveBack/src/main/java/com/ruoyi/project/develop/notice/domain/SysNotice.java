package com.ruoyi.project.develop.notice.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 通知公告表 sys_notice
 * 
 * @author ruoyi
 */
public class SysNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private String id;
    /** 公告标题 */
    private String notice_title;
    /** 公告内容 */
    private String notice_content;
    /** 公告状态（0正常 1关闭） */
    private String status;
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
	public void setId(String id) {
		this.id = id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "SysNotice [id=" + id + ", notice_title=" + notice_title + ", notice_content=" + notice_content
				+ ", status=" + status + ", remark=" + remark + ", cre_date=" + cre_date + ", up_date=" + up_date
				+ ", create_by=" + create_by + ", update_by=" + update_by + "]";
	}
}
