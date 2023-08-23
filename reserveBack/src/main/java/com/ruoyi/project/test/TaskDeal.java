package com.ruoyi.project.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.redis.RedisUtils;


/**
 * @Scheduled定时任务测试
 * @author Administrator
 *
 */
@Component
public class TaskDeal {
	
	 @Autowired
	 private RedisUtils redisUtils;
	
	
	//  每分钟启动 （测试）
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void timerToNow(){
		redisUtils.set("ceshi", "123456789");
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(redisUtils.get("ceshi"));
		System.out.println(MessageUtils.message("login.html_tip1"));
		
		/*FrontUser user = new FrontUser();
		user.setUser_tel("111111111111");
		user.setUser_name("22222222222");
		redisUtils.set("user111", user);
		FrontUser userString =  (FrontUser) redisUtils.get("user111");
		System.out.println(userString);*/
	}
	
	

}
