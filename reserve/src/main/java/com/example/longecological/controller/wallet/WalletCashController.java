package com.example.longecological.controller.wallet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.wallet.WalletCashService;


@Controller
@RequestMapping("/api/wallet/cash")
public class WalletCashController {
	
	@Autowired
	private  WalletCashService walletCashService;
	

	/**
	 * 提现页面加载信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCashPageInfo")
	public R getCashPageInfo(@RequestBody Map<String, Object> map){
		return walletCashService.getCashPageInfo(map);
	}
	/**
	 * 用户申请提现
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userApplyCash")
	public R userApplyCash(@RequestBody Map<String, Object> map) {
		return walletCashService.userApplyCash(map);
	}
	
	
	/**
	 * 查询用户提现记录列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserCashList")
	public R getUserCashList(@RequestBody Map<String, Object> map) {
		return walletCashService.getUserCashList(map);
	}
	/**
	 * 查询用户提现记录详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserCashDetail")
	public R getUserCashDetail(@RequestBody Map<String, Object> map) {
		return walletCashService.getUserCashDetail(map);
	}
	
}
