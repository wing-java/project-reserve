package com.ruoyi.project.develop.statistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitAll;


/**
 * 用户流水总汇总管理
 * @author Administrator
 *
 */
public interface SummaryUserBenefitAllMapper {


	/**
	 * 查询用户流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSummaryUserBenefitAllList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户流水类性汇总列表
	 * @param params
	 * @return
	 */
	List<SummaryUserBenefitAll> selectSummaryUserBenefitAllList(@Param("map") Map<String, Object> params);
	
}
