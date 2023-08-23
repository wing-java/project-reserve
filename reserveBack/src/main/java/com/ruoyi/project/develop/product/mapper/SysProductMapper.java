package com.ruoyi.project.develop.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.product.domain.SysProduct;



/**
 * 产品包信息管理
 * @author Administrator
 *
 */
public interface SysProductMapper {
	
	/**
	 * 查询产品包信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysProductList(@Param("map") Map<String, Object> params);
	/**
	 * 导出产品包信息列表
	 * @param params
	 * @return
	 */
	List<SysProduct> selectSysProductList(@Param("map") Map<String, Object> params);
	/**
	 * 根据编号查询产品包详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysProductById(@Param("goods_id") String goods_id);
	
	
	/**
	 * 新增产品包信息
	 * @param map
	 * @return
	 */
	int addSysProduct(@Param("map") Map<String, Object> map);
	/**
	 * 更新产品包信息
	 * @param map
	 * @return
	 */
	int updateSysProduct(@Param("map") Map<String, Object> map);
	
	
	/**
	 * 更新产品包删除状态
	 * @param goodsMap
	 * @return
	 */
	int updateSysProductDelStatus(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 更新产品包状态（上下架产品包）
	 * @param goodsMap
	 * @return
	 */
	int updateSysProductStatus(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 查询最大排序
	 * @return
	 */
	String getMaxRankSysProduct();
	/**
	 * 查询当前排序产品
	 * @param goodsMap
	 * @return
	 */
	Map<String, Object> getProductInfoByRank(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 更新排序
	 * @param goodsMap
	 * @return
	 */
	int updateSysProductRank(@Param("map") Map<String, Object> map);

}
