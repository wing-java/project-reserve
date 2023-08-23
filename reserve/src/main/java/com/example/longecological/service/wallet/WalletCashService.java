package com.example.longecological.service.wallet;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 用户提现相关
 * @author Administrator
 *
 */
public interface WalletCashService {

	
	/**
	 * 用户提现页面信息加载
	 * @param map
	 * @return
	 */
	R getCashPageInfo(Map<String, Object> map);
	/**
	 * 用户申请提现
	 * @param map
	 * @return
	 */
	R userApplyCash(Map<String, Object> map);


	/**
	 * 查询用户提现记录列表
	 * @param map
	 * @return
	 */
	R getUserCashList(Map<String, Object> map);
	/**
	 * 查询用户提现记录详情
	 * @param map
	 * @return
	 */
	R getUserCashDetail(Map<String, Object> map);

}
