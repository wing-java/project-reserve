package com.ruoyi.project.develop.statistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.project.develop.statistics.domain.SummaryPlatformBenefitEvery;
import com.ruoyi.project.develop.statistics.mapper.SummaryPlatformBenefitMapper;
import com.ruoyi.project.develop.statistics.service.SummaryPlatformBenefitService;


/**
 * 用户流水总汇总管理
 * @author Administrator
 *
 */
@Service
public class SummaryPlatformBenefitServiceImpl implements SummaryPlatformBenefitService {
	
	@Autowired
	private SummaryPlatformBenefitMapper summaryPlatformBenefitMapper;

	
	/**
	 * 查询平台流水类性汇总列表
	 */
	@Override
	public List<Map<String, Object>> getSummaryPlatformBenefitList(Map<String, Object> params) {
		if(TypeStatusConstant.summary_user_benefit_everyday_type_1.equals(StringUtil.getMapValue(params, "summary_type"))) {
			return summaryPlatformBenefitMapper.getSummaryPlatformBenefitEveryDayTotalList(params);//一条数据
		}else {
			return summaryPlatformBenefitMapper.getSummaryPlatformBenefitEveryDayDetailList(params);
		}
	}

	
	/**
	 * 导出平台流水类性汇总列表
	 */
	@Override
	public List<SummaryPlatformBenefitEvery> selectSummaryPlatformBenefitList(Map<String, Object> params) {
		if(TypeStatusConstant.summary_user_benefit_everyday_type_1.equals(StringUtil.getMapValue(params, "summary_type"))) {
			return summaryPlatformBenefitMapper.selectSummaryPlatformBenefitEveryDayTotalList(params);//一条数据
		}else {
			return summaryPlatformBenefitMapper.selectSummaryPlatformBenefitEveryDayDetailList(params);
		}
	}
	
}
