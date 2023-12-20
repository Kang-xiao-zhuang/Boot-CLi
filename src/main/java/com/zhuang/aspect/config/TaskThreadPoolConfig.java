package com.zhuang.aspect.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置属性类
 * 问题解决：https://blog.csdn.net/weixin_44495678/article/details/108142287
 */
@Data
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

}

