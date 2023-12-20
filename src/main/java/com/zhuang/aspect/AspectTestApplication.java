package com.zhuang.aspect;

import com.zhuang.aspect.config.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling //开启定时任务
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class} ) // 开启配置属性支持
public class AspectTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectTestApplication.class, args);
    }

}
