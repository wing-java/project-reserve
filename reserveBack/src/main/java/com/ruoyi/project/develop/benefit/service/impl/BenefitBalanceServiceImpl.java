package com.ruoyi.project.develop.benefit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.benefit.domain.Benefit;
import com.ruoyi.project.develop.benefit.mapper.BenefitBalanceMapper;
import com.ruoyi.project.develop.benefit.service.BenefitBalanceService;


/**
 * 余额流水管理
 * @author Administrator
 *
 */
@Service
public class BenefitBalanceServiceImpl implements BenefitBalanceService {
	
	@Autowired
	private BenefitBalanceMapper benefitBalanceMapper;

	
	/**
	 * 查询余额流水列表
	 */
	@Override
	public List<Map<String, Object>> getBenefitRecordBalanceList(Map<String, Object> params) {
		return benefitBalanceMapper.getBenefitRecordBalanceList(params);
	}

	
	/**
	 * 导出余额流水列表
	 */
	@Override
	public List<Benefit> selectBenefitRecordBalanceList(Map<String, Object> params) {
		return benefitBalanceMapper.selectBenefitRecordBalanceList(params);
	}
	
}
