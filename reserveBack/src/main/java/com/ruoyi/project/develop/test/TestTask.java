package com.ruoyi.project.develop.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.test.service.TestService;

/**
 * 定时任务--测试任务
 * 
 * @author ruoyi
 */
@Component("testTask")
public class TestTask {

	@Autowired
	private TestService testService;
	
	
	
	
}
