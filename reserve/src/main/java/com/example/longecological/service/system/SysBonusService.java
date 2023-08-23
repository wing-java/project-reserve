package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;

public interface SysBonusService {

	/**
	 * 
	 * @param map
	 * @return
	 */
	R getSysBonusList(Map<String, Object> map);


	/**
	 * 
	 * @param map
	 * @return
	 */
	R getSysBonusDetail(Map<String, Object> map);
}
