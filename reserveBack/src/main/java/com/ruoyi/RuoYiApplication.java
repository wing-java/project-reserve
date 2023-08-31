package com.ruoyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)//暴露代理
//@EnableTransactionManagement
//@EnableScheduling
//@EnableCaching
//@MapperScan({ "com.ruoyi.project.*.*.mapper" })
public class RuoYiApplication{

    private static final Logger log = LoggerFactory.getLogger(RuoYiApplication.class);


    public static void main(String[] args) throws UnknownHostException {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext run = SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
        Environment env = run.getEnvironment();
        log.info("----------------------------------------------------------");
        log.info("Application '{}' is running! Access URLs:", env.getProperty("spring.application.name"));
        log.info("Local:  http://127.0.0.1:{} ", env.getProperty("server.port"));
        log.info("External:  http://{}:{} ", InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
        log.info("Application '{}' is running! Access URLs:", env.getProperty("spring.application.name"));
        log.info("----------------------------------------------------------");
    }
}