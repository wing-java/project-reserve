package com.ruoyi.project.develop.statistics.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 参数表 t_summary_user_benefit_all
 * 
 * @author ruoyi
 * @date 2019-05-17
 */
public class SummaryUserRegisterEvery extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	
	/** 创建时间 */
	@Excel(name = "统计日期")
	private String cre_date;
	
	/** 复投 */
	@Excel(name = "注册数量")
	private String register_num;

	public String getCre_date() {
		return cre_date;
	}

	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}

	public String getRegister_num() {
		return register_num;
	}

	public void setRegister_num(String register_num) {
		this.register_num = register_num;
	}

	@Override
	public String toString() {
		return "SummaryUserRegisterEvery [cre_date=" + cre_date + ", register_num=" + register_num + "]";
	}
	
}
