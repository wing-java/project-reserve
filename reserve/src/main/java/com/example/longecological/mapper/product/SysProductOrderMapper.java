package com.example.longecological.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysProductOrderMapper {

	/**
	 * 更新产品库存数量
	 * @param map
	 * @return
	 */
	int updateProductStockAndSales(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存订单信息
	 * @param map
	 * @return
	 */
	int addUserProductOrderInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询用户产品订单信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserProductOrderInfoById(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询产品订单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSysProductOrderList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询证书编号
	 * @return
	 */
	int getSysSharestock();
	
	/**
	 * 更新证书编号
	 * @return
	 */
	int updateSysSharestock();
}
