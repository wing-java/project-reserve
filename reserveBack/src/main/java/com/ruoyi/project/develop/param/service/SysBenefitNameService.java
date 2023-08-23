package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.param.domain.SysBenefitName;

/**
 * 流水类型 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface SysBenefitNameService 
{
	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBenefitTypeList(Map<String, Object> params);

	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitNameList(Map<String, Object> params);

	
	/**
	 * 导出流水类型
	 * @param params
	 * @return
	 */
	List<SysBenefitName> selectSysBenefitNameList(Map<String, Object> params);
	
}
