package com.zhuang.aspect.aspect;

import java.util.HashMap;

/**
 * 获取单例map对象
 */
public class SubmitBufferSingleton {

    private static final HashMap<String, Long> map = new HashMap<>();

    private SubmitBufferSingleton() {
    }

    public static HashMap<String, Long> getInstance() {
        return map;
    }

}
