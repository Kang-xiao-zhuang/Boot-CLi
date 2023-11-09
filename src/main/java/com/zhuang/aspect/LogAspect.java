package com.zhuang.aspect;

import com.zhuang.aspect.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    // 定义切面
    @Pointcut("@annotation(com.zhuang.aspect.annotation.LogAnnotation)")
    private void logAnnotation(){

    }

    @Before("logAnnotation()")
    public void before(){
        log.info("before=================================");
    }


    @After("logAnnotation()")
    public void after(){
        log.info("after===================================");
    }
}
