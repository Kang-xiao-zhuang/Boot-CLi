package com.zhuang.aspect;

import com.zhuang.aspect.config.TestClass;
import com.zhuang.aspect.task.AsyncTask;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

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
        System.out.println("获得结果为：" + testClass.test1("111"));
        long t2 = System.currentTimeMillis();
        System.out.println("执行时间为：" + (t2 - t1));

        t1 = System.currentTimeMillis();
        System.out.println("获得结果为：" + testClass.test1("111"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为：" + (t2 - t1));

        Thread.sleep(1000); //休眠一秒，看缓存是否失效

        t1 = System.currentTimeMillis();
        System.out.println("获得结果为：" + testClass.test1("111"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为：" + (t2 - t1));

        //测试两个key相同，参数不同的方法
        t1 = System.currentTimeMillis();
        System.out.println("获得结果为：" + testClass.test2("111", "222"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为：" + (t2 - t1));

        //测试两个key不同同，参数相同同的方法
        t1 = System.currentTimeMillis();
        System.out.println("获得结果为：" + testClass.test3("111", "222"));
        t2 = System.currentTimeMillis();
        System.out.println("执行时间为：" + (t2 - t1));
    }


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void AsyncTaskTest() {

        for (int i = 0; i < 100; i++) {
            asyncTask.doTask1(i);
        }
        logger.info("All tasks finished.");
    }

    @Test
    public void AsyncTaskNativeTest() {

        for (int i = 0; i < 100; i++) {
            asyncTask.doTask2(i);
        }
        logger.info("All tasks finished.");
    }


}
