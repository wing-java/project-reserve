package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysChannelService;

/**
 * 渠道
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/channel")
public class SysChannelController {

	@Autowired
	SysChannelService sysChannelService;

	/**
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysChannelList")
	public R getSysChannelList(@RequestBody Map<String, Object> map) {
		return sysChannelService.getSysChannelList(map);
	}
}
