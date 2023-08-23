package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

public interface UserOrderMapper {

	List<Map<String, Object>> getUserOrderDayBenefitList();
	
	List<Map<String, Object>> getUserOrderYearBenefitList();
	
	List<Map<String, Object>> getUserOrderSettleList();
}
