package com.ruoyi.project.develop.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 合同 数据层
 * 
 * @author ruoyi
 */
public interface SysContractMapper
{

	/**
	 * 查询合同列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSysContractList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询合同详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getContractById(@Param("contract_id") String contract_id);

	
	/**
	 * 新增合同
	 * @param params
	 * @return
	 */
	public int addSysContract(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新合同
	 * @param params
	 * @return
	 */
	public int updateSysContract(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除合同
	 * @param notice_id
	 * @return
	 */
	public int deleteSysContract(@Param("contract_id") String contract_id);


	/**
	 * 根据合同类型查询详情
	 * @param string
	 * @return
	 */
	public Map<String, Object> getContractByType(@Param("contract_type") String contract_type);

}