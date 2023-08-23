package com.example.longecological.config.delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/**
 * @Desc 先把Bean注入，不然 @Autowired   private ExecutorService executorService; 会报错，
 **/
@Configuration //@Configuration 与 @Component 均可
//@Component
public class ThreadPoolConfig {
    
	@Bean
    public ExecutorService getThreadPool() {
        System.out.println("ExecutorService getThreadPool()...");
        return new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }
}