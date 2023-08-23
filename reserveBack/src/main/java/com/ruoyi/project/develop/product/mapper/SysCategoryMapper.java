package com.ruoyi.project.develop.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.product.domain.SysCategory;

public interface SysCategoryMapper {

	/**
	 * 查询产品包信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCategoryList(@Param("map") Map<String, Object> params);
	/**
	 * 导出产品包信息列表
	 * @param params
	 * @return
	 */
	List<SysCategory> selectSysCategoryList(@Param("map") Map<String, Object> params);
	/**
	 * 根据编号查询产品包详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCategoryById(@Param("category_id") String goods_id);
	
	
	/**
	 * 新增产品包信息
	 * @param map
	 * @return
	 */
	int addSysCategory(@Param("map") Map<String, Object> map);
	/**
	 * 更新产品包信息
	 * @param map
	 * @return
	 */
	int updateSysCategory(@Param("map") Map<String, Object> map);
	
	
	/**
	 * 更新产品包删除状态
	 * @param goodsMap
	 * @return
	 */
	int updateSysCategoryDelStatus(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 更新产品包状态（上下架产品包）
	 * @param goodsMap
	 * @return
	 */
	int updateSysCategoryStatus(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 更新停售状态
	 * @param goodsMap
	 * @return
	 */
	int updateSysCategoryIsSales(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 查询最大排序
	 * @return
	 */
	String getMaxRankSysCategory();
	/**
	 * 查询当前排序产品
	 * @param goodsMap
	 * @return
	 */
	Map<String, Object> getCategoryInfoByRank(@Param("map") Map<String, Object> goodsMap);
	/**
	 * 更新排序
	 * @param goodsMap
	 * @return
	 */
	int updateSysCategoryRank(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除
	 * @param goods_id
	 * @return
	 */
	int deleteSysCategory(@Param("category_id") String category_id);
}
