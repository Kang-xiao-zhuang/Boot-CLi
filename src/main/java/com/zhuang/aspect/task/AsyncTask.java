package com.zhuang.aspect.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //myTaskAsyncPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("myTaskAsyncPool")
    public void doTask1(int i) {
        logger.info("Task" + i + " started.");
    }

    // spring原生
    @Async
    public void doTask2(int i) {
        logger.info("Task2-Native" + i + " started.");
    }
}
