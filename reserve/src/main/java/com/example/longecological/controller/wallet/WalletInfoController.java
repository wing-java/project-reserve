package com.example.longecological.controller.wallet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.wallet.WalletInfoService;


@Controller
@RequestMapping("/api/wallet/info")
public class WalletInfoController {

	@Autowired
	private  WalletInfoService walletInfoService;
	

	/**
	 * 查询流水记录列表
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getWalletRecordList")
	public R getWalletRecordList(@RequestBody Map<String, Object> map){
		return walletInfoService.getWalletRecordList(map);
	}
	
}
