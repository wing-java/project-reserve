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
import com.example.longecological.service.system.SysNoticeService;


/**
 * 公告通知相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/system/notice")
public class SysNoticeController {
	
	@Autowired
	private SysNoticeService sysNoticeService;

	
	/**
	 * 跳转到系统公告页面
	 * @return
	 */
	@RequestMapping("/toNoticeCenter")
	public String toNoticeCenter() {
		return "system/notice/notice-center";
	}
	/**
	 * 跳转到查看公告详情页面
	 * @return
	 */
	@RequestMapping("/toSysNoticeDetail")
	public String toSysNoticeDetail(@RequestParam Map<String, Object> map, Model model) {
		model.addAttribute("map", map);
		return "system/notice/notice-detail";
	}
	
	
	/**
	 * 查询系统最新公告======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewNotice")
	public R getNewNotice(@RequestBody Map<String, Object> map) {
		return sysNoticeService.getNewNotice(map);
	}
	/**
	 * 查询系统公告列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysNoticeList")
	public R getSysNoticeList(@RequestBody Map<String, Object> map) {
		return sysNoticeService.getSysNoticeList(map);
	}
	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSysNoticeDetail")
	public R getSysNoticeDetail(@RequestBody Map<String, Object> map) {
		return sysNoticeService.getSysNoticeDetail(map);
	}
	
}
