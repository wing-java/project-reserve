package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysBenefitParamYear;

public interface SysBenefitParamYearService {
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitParamYearList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysBenefitParamYear> selectSysBenefitParamYearList(Map<String, Object> params);


	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysBenefitParamYearById(String id);


	/**
	 * 编辑
	 * @param params
	 * @return
	 */
	R editSave(Map<String, Object> map);
}
