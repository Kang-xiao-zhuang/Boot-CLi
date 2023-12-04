package com.zhuang.aspect.task;


import com.zhuang.aspect.aspect.SubmitBufferSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class NoRepeatSubmitTask {

    @Scheduled(cron = "* * * * * ?")
    public void start() {
        HashMap<String, Long> hashMap = SubmitBufferSingleton.getInstance();
        log.warn(hashMap.toString());
        for (Map.Entry<String, Long> next : hashMap.entrySet()) {
            String key = next.getKey();
            Long value = next.getValue();
            if (value > Instant.now().toEpochMilli()) {
                hashMap.remove(key);
            }
        }
        // 如果对时间没有特别严格的要求就直接clear
//        hashMap.clear();
    }
}