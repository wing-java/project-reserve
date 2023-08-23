package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.common.SysParamService;

/**
 * 系统参数控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/common/sysParam")
public class SysParamController {
	
	@Autowired
	private SysParamService sysParamService;
	
	
	/**
	 * 根据参数代码查询参数值
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getParamByCode")
	public R getParamByCode(@RequestBody Map<String, Object> map) {
		return sysParamService.getParamByCode(map);
	}

}
