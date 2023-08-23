package com.example.longecological.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysProductCategoryMapper {

	/**
	 * 查询类型列表
	 * @return
	 */
	List<Map<String, Object>> getCategoryList();
}
