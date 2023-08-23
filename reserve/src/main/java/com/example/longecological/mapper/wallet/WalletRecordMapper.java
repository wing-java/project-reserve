package com.example.longecological.mapper.wallet;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户流水管理
 * @author Administrator
 *
 */
public interface WalletRecordMapper {


	/**
	 * 查询余额流水列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getWalletRecordList(@Param("map") Map<String, Object> map);
	
}
