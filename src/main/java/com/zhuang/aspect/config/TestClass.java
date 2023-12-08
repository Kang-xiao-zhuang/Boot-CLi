package com.zhuang.aspect.config;

import com.zhuang.aspect.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

// redis测试类
@Component
public class TestClass {

    @Cacheable(key = "key1", param = {0})
    public String test1(String param1) throws InterruptedException {
        System.out.println("重新获得值。。。。test1");
        Thread.sleep(100);
        return String.valueOf(new Random().nextInt());
    }

    @Cacheable(key = "key1", param = {0, 1})
    public String test2(String param1, String param2) throws InterruptedException {
        System.out.println("重新获得值。。。。test2");
        Thread.sleep(100);
        return String.valueOf(new Random().nextInt());
    }

    @Cacheable(key = "key2", param = {0, 1})
    public String test3(String param1, String param2) throws InterruptedException {
        System.out.println("重新获得值。。。。test3");
        Thread.sleep(100);
        return String.valueOf(new Random().nextInt());
    }
}
