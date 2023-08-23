package com.example.longecological;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
@ServletComponentScan
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan({ "com.example.longecological.mapper", "com.example.longecological.mapper.*" })
public class LongEcologicalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LongEcologicalApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    springboot启动成功      ヾ(◍°∇°◍)ﾉﾞ\n"
				+ "  .   ____          _            __ _ _\n" + " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\\n"
				+ "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\\n" + " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )\n"
				+ "  '  |____| .__|_| |_|_| |_\\__, | / / / /\n" + " =========|_|==============|___/=/_/_/_/");
	}
	
	
	/**
	 * 测试自动注入的是 PlatformTransactionManager 接口的哪个实现类。
	 * @param platformTransactionManager
	 * @return
	 */
	/*@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }*/

	

	//  打成war包需要的
	/*protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(longecologicalApplication.class);
	}*/
}
