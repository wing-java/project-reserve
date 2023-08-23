package com.example.longecological.controller.wallet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.wallet.WalletRollService;


@Controller
@RequestMapping("/api/wallet/roll")
public class WalletRollController {

	@Autowired
	private  WalletRollService walletRollService;
	

	/**
	 * 互转页面加载信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRollPageInfo")
	public R getRollPageInfo(@RequestBody Map<String, Object> map){
		return walletRollService.getRollPageInfo(map);
	}
	/**
	 * 用户申请互转
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userApplyRoll")
	public R userApplyRoll(@RequestBody Map<String, Object> map){
		return walletRollService.userApplyRoll(map);
	}
	/**
	 * 查询用户转账记录列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserRollLogList")
	public R getUserRollLogList(@RequestBody Map<String, Object> map){
		return walletRollService.getUserRollLogList(map);
	}
	
}
