package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysBenefitParamYear;

public interface SysBenefitParamYearMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitParamYearList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysBenefitParamYear> selectSysBenefitParamYearList(@Param("map") Map<String, Object> params);


	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysBenefitParamYearById(@Param("id") String id);


	/**
	 * 更新
	 * @param params
	 * @return
	 */
	int updateSysBenefitParamYear(@Param("map") Map<String, Object> params);
}
