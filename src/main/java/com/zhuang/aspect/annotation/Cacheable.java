package com.zhuang.aspect.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {
    String key(); //缓存的key值

    int[] param() default {}; //影响key的参数

    long timeout() default 1L; //超时时间

    TimeUnit timeUnit() default TimeUnit.SECONDS;  //超时时间单位
}
