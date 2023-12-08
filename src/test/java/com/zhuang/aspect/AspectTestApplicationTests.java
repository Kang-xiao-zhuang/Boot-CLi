package com.zhuang.aspect;

import com.zhuang.aspect.config.TestClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AspectTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TestClass testClass;

    @Test
    public void test() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        System.out.println("获得结果为："+testClass.test1("111"));
        long t2 = System.currentTimeMillis();
        System.out.println("执行时间为："+(t2 - t1));

        t1 = System.currentTimeMillis();
        System.out.println("获得结果为："+testClass.test1("111"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为："+(t2 - t1));

        Thread.sleep(1000); //休眠一秒，看缓存是否失效

        t1 = System.currentTimeMillis();
        System.out.println("获得结果为："+testClass.test1("111"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为："+(t2 - t1));

        //测试两个key相同，参数不同的方法
        t1 = System.currentTimeMillis();
        System.out.println("获得结果为："+testClass.test2("111","222"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为："+(t2 - t1));

        //测试两个key不同同，参数相同同的方法
        t1 = System.currentTimeMillis();
        System.out.println("获得结果为："+testClass.test3("111","222"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为："+(t2 - t1));
    }

}
