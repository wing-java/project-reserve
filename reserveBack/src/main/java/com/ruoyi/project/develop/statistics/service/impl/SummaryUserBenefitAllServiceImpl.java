package com.ruoyi.project.develop.statistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitAll;
import com.ruoyi.project.develop.statistics.mapper.SummaryUserBenefitAllMapper;
import com.ruoyi.project.develop.statistics.service.SummaryUserBenefitAllService;


/**
 * 用户流水总汇总管理
 * @author Administrator
 *
 */
@Service
public class SummaryUserBenefitAllServiceImpl implements SummaryUserBenefitAllService {
	
	@Autowired
	private SummaryUserBenefitAllMapper summaryUserBenefitAllMapper;

	
	/**
	 * 查询用户流水类性汇总列表
	 */
	@Override
	public List<Map<String, Object>> getSummaryUserBenefitAllList(Map<String, Object> params) {
		return summaryUserBenefitAllMapper.getSummaryUserBenefitAllList(params);
	}

	
	/**
	 * 导出用户流水类性汇总列表
	 */
	@Override
	public List<SummaryUserBenefitAll> selectSummaryUserBenefitAllList(Map<String, Object> params) {
		return summaryUserBenefitAllMapper.selectSummaryUserBenefitAllList(params);
	}
	
}
