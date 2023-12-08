package com.zhuang.aspect.aspect;

import com.zhuang.aspect.annotation.Cacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class CacheAspect {

    //缓存map,因为可能多线程调用,所以用ConcurrentHashMap
    private final Map<String, CacheObject> CACHE = new ConcurrentHashMap<>();

    @Around(value = "@annotation(cacheable)")
    public Object process(ProceedingJoinPoint pjp, Cacheable cacheable) throws Throwable {
        //检查一下注解的参数是否有效
        if (isCacheParamOutOfIndex(cacheable.param(), cacheable.param())) {
            return pjp.proceed();
        }
        //根据注解的定义参数和请求方法的参数获得缓存的key值
        String key = getKey(cacheable, cacheable.param(), cacheable.param());

        Object obj = get(key);
        if (obj != null) {
            //如果缓存里有值,则直接返回
            return obj;
        }
        obj = pjp.proceed();
        //记得将结果存入缓存中
        set(key, obj, cacheable.timeout(), cacheable.timeUnit());
        return obj;
    }

    private boolean isCacheParamOutOfIndex(int[] lable, int[] params) {
        for (int index : lable) {
            if (index >= params.length) {
                return true;
            }
        }
        return false;
    }

    private String getKey(Cacheable cacheable, int[] lable, int[] params) {
        StringBuilder builder = new StringBuilder("cache_" + cacheable.key());
        for (int index : lable) {
            //不要忘记把参数也加入key
            builder.append("_");
            builder.append(params[index]);
        }

        return builder.toString();

    }

    private void set(String s, Object obj, long timeout, TimeUnit timeUnit) {
        //推荐使用redis等缓存工具实现
        CACHE.put(s, new CacheObject(obj, timeout, timeUnit));
    }

    private Object get(String s) {
        //推荐使用redis等缓存工具实现
        CacheObject cacheObject = CACHE.get(s);
        if (cacheObject == null || cacheObject.isTimeOut())
            return null;
        return cacheObject.getObj();
    }

    /**
     * 内部辅助类
     */
    private static class CacheObject {
        private final Object obj;

        private final long cacheTimeMillis;

        private final long timeOutTime;

        private final TimeUnit timeUnit;

        CacheObject(Object obj, long timeOutTime, TimeUnit timeUnit) {
            this.obj = obj;
            this.cacheTimeMillis = System.currentTimeMillis();
            this.timeOutTime = timeOutTime;
            this.timeUnit = timeUnit;
        }

        Object getObj() {
            return obj;
        }

        boolean isTimeOut() {
            return System.currentTimeMillis() > cacheTimeMillis + timeUnit.toMillis(timeOutTime);
        }
    }
}
