package com.zhuang.aspect.aspect;

import com.zhuang.aspect.annotation.RequestLimit;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;

@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAop {

    // 定义切面
    @Pointcut("@annotation(com.zhuang.aspect.annotation.RequestLimit)")
    private void noRepeatSubmitAop() {

    }


    @Synchronized // 作用是创建一个互斥锁，保证只有一个线程对 SubmitBufferSingleton.getInstance() 这个变量进行修改。
    @Around("noRepeatSubmitAop()&&@annotation(nrs)")
    public Object around(ProceedingJoinPoint pjp, RequestLimit nrs) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String key = getIp(request) + " ：" + request.getServletPath();
        long time = nrs.time();
        Object o = pjp.proceed();
        HashMap<String, Long> hashMap = SubmitBufferSingleton.getInstance();
        long nowTime = Instant.now().toEpochMilli();
        if (!hashMap.containsKey(key)) {
            hashMap.put(key, nowTime + time);
            return o;
        } else {
            if (nowTime > hashMap.get(key)) {
                hashMap.put(key, nowTime + time);
                return o;
            } else {
                log.error("操作过于频繁 {}", key);
                return "操作过于频繁";
            }
        }

    }


    // 获取调用者ip
    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
