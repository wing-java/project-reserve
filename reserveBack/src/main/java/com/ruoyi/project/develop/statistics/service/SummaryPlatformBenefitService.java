package com.ruoyi.project.develop.statistics.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.statistics.domain.SummaryPlatformBenefitEvery;

/**
 * 平台流水总汇总管理
 * @author Administrator
 *
 */
public interface SummaryPlatformBenefitService {

	
	/**
	 * 查询平台流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryPlatformBenefitList(Map<String, Object> params);

	
	/**
	 * 导出平台流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<SummaryPlatformBenefitEvery> selectSummaryPlatformBenefitList(Map<String, Object> params);
	
}
	
	
