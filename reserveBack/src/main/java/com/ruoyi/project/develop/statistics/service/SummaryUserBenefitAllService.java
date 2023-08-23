package com.ruoyi.project.develop.statistics.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitAll;

/**
 * 用户流水总汇总管理
 * @author Administrator
 *
 */
public interface SummaryUserBenefitAllService {

	
	/**
	 * 查询用户流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserBenefitAllList(Map<String, Object> params);

	
	/**
	 * 导出用户流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<SummaryUserBenefitAll> selectSummaryUserBenefitAllList(Map<String, Object> params);
	
}
	
	
