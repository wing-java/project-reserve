package com.ruoyi.project.develop.statistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.develop.statistics.domain.SummaryUserBenefitEvery;
import com.ruoyi.project.develop.statistics.mapper.SummaryUserBenefitEveryMapper;
import com.ruoyi.project.develop.statistics.service.SummaryUserBenefitEveryService;


/**
 * 用户流水每日汇总管理
 * @author Administrator
 *
 */
@Service
public class SummaryUserBenefitEveryServiceImpl implements SummaryUserBenefitEveryService {
	
	@Autowired
	private SummaryUserBenefitEveryMapper summaryUserBenefitEveryMapper;

	
	/**
	 * 查询用户流水类性汇总列表
	 */
	@Override
	public List<Map<String, Object>> getSummaryUserBenefitEveryList(Map<String, Object> params) {
		return summaryUserBenefitEveryMapper.getSummaryUserBenefitEveryList(params);
	}

	
	/**
	 * 导出用户流水类性汇总列表
	 */
	@Override
	public List<SummaryUserBenefitEvery> selectSummaryUserBenefitEveryList(Map<String, Object> params) {
		return summaryUserBenefitEveryMapper.selectSummaryUserBenefitEveryList(params);
	}
	
}
