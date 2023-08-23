package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysBenefitParamAlgebra;

public interface SysBenefitParamAlgebraMapper {
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitParamAlgebraList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysBenefitParamAlgebra> selectSysBenefitParamAlgebraList(@Param("map") Map<String, Object> params);


	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysBenefitParamAlgebraById(@Param("id") String id);


	/**
	 * 更新
	 * @param params
	 * @return
	 */
	int updateSysBenefitParamAlgebra(@Param("map") Map<String, Object> params);

}
