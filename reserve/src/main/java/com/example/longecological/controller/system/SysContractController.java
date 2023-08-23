package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysContractService;


/**
 * 系统协议合同相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/contract")
public class SysContractController {
	
	@Autowired
	private SysContractService sysContractService;

	
	/**
	 * 根据类型查询系统协议合同信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysContractByType")
	public R getSysContractByType(@RequestBody Map<String, Object> map) {
		return sysContractService.getSysContractByType(map);
	}
	
}
