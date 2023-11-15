package com.zhuang.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(999)
public class LogAspect {

    // 定义切面
    @Pointcut("@annotation(com.zhuang.aspect.annotation.LogAnnotation)")
    private void logAspect() {

    }

    @Before("logAspect()")
    public void doAround(JoinPoint jpt) throws Throwable {
        // 获取传过来的参数，在执行service方法前执行
        Object[] args = jpt.getArgs();
        System.out.println(Arrays.toString(args));
        log.info(jpt.toString());
    }

    @AfterThrowing(value = "logAspect()", throwing = "e")
    public void logAfterThrowing(JoinPoint jpt, Exception e) {
        log.error(jpt.getSignature().getName());
        log.info(e.getMessage());
    }
}
