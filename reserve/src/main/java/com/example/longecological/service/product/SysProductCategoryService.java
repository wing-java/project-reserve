package com.example.longecological.service.product;

import java.util.Map;

import com.example.longecological.entity.R;

public interface SysProductCategoryService {

	/**
	 * 查询商品类型
	 * @param map
	 * @return
	 */
	R getCategoryList(Map<String, Object> map);
}
