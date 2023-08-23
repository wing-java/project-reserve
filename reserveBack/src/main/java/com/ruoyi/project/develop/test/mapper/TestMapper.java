package com.ruoyi.project.develop.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TestMapper {

	
	/**
	 * 更新用户订单状态
	 * @param userMap
	 * @return
	 */
	int updateUserOrderInfoStatus(@Param("map") Map<String, Object> userMap);
	
	/**
	 * 
	 * @return
	 */
	List<Map<String, Object>> getOrderList();

	

}
