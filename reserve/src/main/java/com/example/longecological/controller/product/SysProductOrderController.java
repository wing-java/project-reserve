package com.example.longecological.controller.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.product.SysProductOrderService;

@Controller
@RequestMapping("/api/productOrder")
public class SysProductOrderController {
	
	@Autowired
	SysProductOrderService sysProductOrderService;

	/**
	 * 立即购买
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitOrder")
	public R submitOrder(@RequestBody Map<String, Object> map) {
		return sysProductOrderService.submitOrder(map);
	}
	
	
	/**
	 * 查询订单详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserProductOrderDetail")
	public R getUserProductOrderDetail(@RequestBody Map<String, Object> map) {
		return sysProductOrderService.getUserProductOrderDetail(map);
	}
	
	/**
	 * 查询订单列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysProductOrderList")
	public R getSysProductOrderList(@RequestBody Map<String, Object> map) {
		return sysProductOrderService.getSysProductOrderList(map);
	}
	
	/**
	 * 领取收益
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/receiveUnclaimedBenefit")
	public R receiveUnclaimedBenefit(@RequestBody Map<String, Object> map) {
		return sysProductOrderService.receiveUnclaimedBenefit(map);
	}
	
}
