package com.example.longecological.service.wallet;

import java.util.Map;

import com.example.longecological.entity.R;

/**
 * 用户互转相关
 * @author Administrator
 *
 */
public interface WalletRollService {

	
	/**
	 * 互转页面信息加载
	 * @param map
	 * @return
	 */
	R getRollPageInfo(Map<String, Object> map);


	/**
	 * 用户申请互转
	 * @param map
	 * @return
	 */
	R userApplyRoll(Map<String, Object> map);

	
	/**
	 * 查询用户转账记录列表
	 * @param map
	 * @return
	 */
	R getUserRollLogList(Map<String, Object> map);

}
