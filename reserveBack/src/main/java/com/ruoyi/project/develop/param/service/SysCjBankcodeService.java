package com.ruoyi.project.develop.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.param.domain.SysCjBankcode;

public interface SysCjBankcodeService {

	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSysCjBankcodeList(Map<String, Object> params);

	
	/**
	 * 导出
	 * @param params
	 * @return
	 */
	List<SysCjBankcode> selectSysCjBankcodeList(Map<String, Object> params);

	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getSysCjBankcodeById(String id);

	
	/**
	 * 编辑
	 * @param params
	 * @return
	 */
	R editSysCjBankcode(Map<String, Object> params);

	
	/**
	 * 新增
	 * @param params
	 * @return
	 */
	R addSysCjBankcode(Map<String, Object> params);


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	R batchRemoveSysCjBankcode(String ids);
}
