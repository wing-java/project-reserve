package com.ruoyi.project.develop.statistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.statistics.domain.SummaryPlatformBenefitEvery;


/**
 * 平台流水总汇总管理
 * @author Administrator
 *
 */
public interface SummaryPlatformBenefitMapper {


	/**
	 * 平台流水类型每日汇总：平台总汇总
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryPlatformBenefitEveryDayTotalList(@Param("map") Map<String, Object> params);


	/**
	 * 平台流水类型每日汇总：平台明细汇总
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryPlatformBenefitEveryDayDetailList(@Param("map") Map<String, Object> params);


	/**
	 * 导出平台流水类型每日汇总：平台总汇总
	 * @param params
	 * @return
	 */
	List<SummaryPlatformBenefitEvery> selectSummaryPlatformBenefitEveryDayTotalList(@Param("map") Map<String, Object> params);


	/**
	 * 导出平台流水类型每日汇总：平台明细汇总
	 * @param params
	 * @return
	 */
	List<SummaryPlatformBenefitEvery> selectSummaryPlatformBenefitEveryDayDetailList(@Param("map") Map<String, Object> params);
	
}
