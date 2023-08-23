package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysBenefitParamAlgebra;

public interface SysBenefitParamAlgebraService {
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitParamAlgebraList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysBenefitParamAlgebra> selectSysBenefitParamAlgebraList(Map<String, Object> params);


	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysBenefitParamAlgebraById(String id);


	/**
	 * 编辑
	 * @param params
	 * @return
	 */
	R editSave(Map<String, Object> map);
}
