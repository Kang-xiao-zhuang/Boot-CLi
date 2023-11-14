package com.zhuang.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(999)
public class LogAspect {

    // 定义切面
    @Pointcut("@annotation(com.zhuang.aspect.annotation.LogAnnotation)")
    private void logAspect(){

    }

    @Before("logAspect()")
    public void doAround(JoinPoint jpt) throws Throwable{
        log.info(jpt.toString());
    }

   @AfterThrowing(value = "logAspect()",throwing = "e")
    public void logAfterThrowing(JoinPoint jpt,Exception e){
        log.error(jpt.getSignature().getName());
        log.info(e.getMessage());
    }
}
