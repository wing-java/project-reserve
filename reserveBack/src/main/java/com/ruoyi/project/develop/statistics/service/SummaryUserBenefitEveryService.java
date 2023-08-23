package com.ruoyi.project.develop.statistics.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitEvery;

/**
 * 用户流水每日汇总管理
 * @author Administrator
 *
 */
public interface SummaryUserBenefitEveryService {

	
	/**
	 * 查询用户流水类型每日汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserBenefitEveryList(Map<String, Object> params);

	
	/**
	 * 导出用户流水类型每日汇总列表
	 * @param params
	 * @return
	 */
	List<SummaryUserBenefitEvery> selectSummaryUserBenefitEveryList(Map<String, Object> params);
	
}
	
	
