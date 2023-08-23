package com.example.longecological.controller.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.product.SysProductCategoryService;

@Controller
@RequestMapping("/api/product/category")
public class SysProductCategoryController {

	@Autowired
	SysProductCategoryService sysProductCategoryService;
	
	/**
	 * 查询平台产品列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCategoryList")
	public R getCategoryList(@RequestBody Map<String, Object> map) {
		return sysProductCategoryService.getCategoryList(map);
	}
}
