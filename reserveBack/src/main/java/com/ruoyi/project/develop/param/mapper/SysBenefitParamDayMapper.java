package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysBenefitParamDay;

public interface SysBenefitParamDayMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitParamDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysBenefitParamDay> selectSysBenefitParamDayList(@Param("map") Map<String, Object> params);


	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysBenefitParamDayById(@Param("id") String id);


	/**
	 * 更新
	 * @param params
	 * @return
	 */
	int updateSysBenefitParamDay(@Param("map") Map<String, Object> params);
}
