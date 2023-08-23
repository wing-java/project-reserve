package com.ruoyi.project.develop.statistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitEvery;


/**
 * 用户流水总汇总管理
 * @author Administrator
 *
 */
public interface SummaryUserBenefitEveryMapper {


	/**
	 * 查询用户流水类型每日汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserBenefitEveryList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户流水类型每日汇总列表
	 * @param params
	 * @return
	 */
	List<SummaryUserBenefitEvery> selectSummaryUserBenefitEveryList(@Param("map") Map<String, Object> params);
	
}
