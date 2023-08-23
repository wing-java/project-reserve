package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.common.SysFunctionLockParamService;

/**
 * 系统参数控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/common/sysFunctionLockParam")
public class SysFunctionLockParamController {
	
	@Autowired
	private SysFunctionLockParamService sysFunctionLockParamService;
	
	
	/**
	 * 根据参数代码查询功能开关
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFunctionLockParamByCode")
	public R getFunctionLockParamByCode(@RequestBody Map<String, Object> map) {
		return sysFunctionLockParamService.getFunctionLockParamByCode(map);
	}

}
