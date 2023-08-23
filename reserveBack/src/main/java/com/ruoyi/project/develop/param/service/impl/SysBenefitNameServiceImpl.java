package com.ruoyi.project.develop.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.project.develop.param.domain.SysBenefitName;
import com.ruoyi.project.develop.param.mapper.SysBenefitNameMapper;
import com.ruoyi.project.develop.param.service.SysBenefitNameService;

/**
 * 流水类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class SysBenefitNameServiceImpl implements SysBenefitNameService 
{
	
	@Autowired
	private SysBenefitNameMapper sysBenefitNameMapper;

	
	/**
	 * 查询流水类型列表
	 */
	@Override
	public List<Map<String, Object>> getBenefitTypeList(Map<String, Object> map) {
		return sysBenefitNameMapper.getBenefitTypeList(map);
	}


	/**
	 * 查询流水类型列表
	 */
	@Override
	public List<Map<String, Object>> getSysBenefitNameList(Map<String, Object> params) {
		return sysBenefitNameMapper.getSysBenefitNameList(params);
	}


	/**
	 * 导出流水类型列表
	 */
	@Override
	public List<SysBenefitName> selectSysBenefitNameList(Map<String, Object> params) {
		return sysBenefitNameMapper.selectSysBenefitNameList(params);
	}
	
}
