package com.ruoyi.project.develop.chart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.develop.chart.service.ChartPurseInfoService;

/**
 * 定存钱包流水操作处理
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/develop/chartPurse")
public class ChartPurseInfoController extends BaseController
{
	
	@Autowired
	private ChartPurseInfoService chartPurseInfoService;
	
	
}
