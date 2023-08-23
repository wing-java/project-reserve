package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysNewsService;


/**
 * 新闻通知相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/news")
public class SysNewsController {
	
	@Autowired
	private SysNewsService sysNewsService;
	
	/**
	 * 查询系统最新新闻======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewNews")
	public R getNewNews(@RequestBody Map<String, Object> map) {
		return sysNewsService.getNewNews(map);
	}
	
	
	/**
	 * 查询系统新闻列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysNewsList")
	public R getSysNewsList(@RequestBody Map<String, Object> map) {
		return sysNewsService.getSysNewsList(map);
	}
	
	/**
	 * 查询系统新闻详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysNewsDetail")
	public R getSysNewsDetail(@RequestBody Map<String, Object> map) {
		return sysNewsService.getSysNewsDetail(map);
	}
	
}
