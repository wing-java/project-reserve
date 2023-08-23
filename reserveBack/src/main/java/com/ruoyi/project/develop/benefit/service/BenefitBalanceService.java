package com.ruoyi.project.develop.benefit.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.benefit.domain.Benefit;

/**
 * 余额流水管理
 * @author Administrator
 *
 */
public interface BenefitBalanceService {

	
	/**
	 * 查询余额流水列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBenefitRecordBalanceList(Map<String, Object> params);

	
	/**
	 * 导出余额流水列表
	 * @param params
	 * @return
	 */
	List<Benefit> selectBenefitRecordBalanceList(Map<String, Object> params);
	
}
	
	
