package com.example.longecological.service.wallet;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 用户流水相关
 * @author Administrator
 *
 */
public interface WalletInfoService {
	
	
	/**
	 * 查询用户流水记录列表
	 * @param map
	 * @return
	 */
	R getWalletRecordList(Map<String, Object> map);

}
