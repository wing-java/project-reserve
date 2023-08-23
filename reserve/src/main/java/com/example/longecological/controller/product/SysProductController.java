package com.example.longecological.controller.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.product.SysProductService;

@Controller
@RequestMapping("/api/product")
public class SysProductController {

	@Autowired
	SysProductService sysProductService;
	
	/**
	 * 查询平台产品列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysProductList")
	public R getSysProductList(@RequestBody Map<String, Object> map) {
		return sysProductService.getSysProductList(map);
	}
	
	/**
	 * 查询平台产品详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysProductDetail")
	public R getSysProductDetail(@RequestBody Map<String, Object> map) {
		return sysProductService.getSysProductDetail(map);
	}
}
