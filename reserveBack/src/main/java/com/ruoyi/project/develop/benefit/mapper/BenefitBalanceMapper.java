package com.ruoyi.project.develop.benefit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.benefit.domain.Benefit;


/**
 * 余额流水管理
 * @author Administrator
 *
 */
public interface BenefitBalanceMapper {


	/**
	 * 查询余额流水列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getBenefitRecordBalanceList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出余额流水列表
	 * @param params
	 * @return
	 */
	List<Benefit> selectBenefitRecordBalanceList(@Param("map") Map<String, Object> params);
	
}
