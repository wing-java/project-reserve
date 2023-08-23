package com.ruoyi.project.develop.task.mapper;

import org.apache.ibatis.annotations.Param;

public interface BackOrClearDealMapper {
	
	
	/**
	 * 备份验证码
	 * @return
	 */
	int backUpVerifyRecord(@Param("date") String date);
	/**
	 * 删除短信验证码主表数据
	 * @return
	 */
	int delVerifyRecord(@Param("date") String date);

	
}
