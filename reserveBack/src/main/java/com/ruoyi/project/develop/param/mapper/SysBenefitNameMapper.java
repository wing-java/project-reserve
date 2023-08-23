package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysBenefitName;

/**
 * 流水类型 数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface SysBenefitNameMapper 
{

	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBenefitTypeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysBenefitNameList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出流水类型列表
	 * @param params
	 * @return
	 */
	List<SysBenefitName> selectSysBenefitNameList(@Param("map") Map<String, Object> params);
}