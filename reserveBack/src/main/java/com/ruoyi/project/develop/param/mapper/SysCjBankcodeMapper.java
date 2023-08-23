package com.ruoyi.project.develop.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.develop.param.domain.SysCjBankcode;

public interface SysCjBankcodeMapper {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCjBankcodeList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysCjBankcode> selectSysCjBankcodeList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCjBankcodeById(@Param("cjBankcode_id") String cjBankcode_id);


	/**
	 * 更新
	 * @param params
	 * @return
	 */
	int updateSysCjBankcode(@Param("map") Map<String, Object> params);


	/**
	 * 新增
	 * @param params
	 * @return
	 */
	int addSysCjBankcode(@Param("map") Map<String, Object> params);


	/**
	 * 
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteSysCjBankcode(@Param("cjBankcode_id") String cjBankcode_id);
}
