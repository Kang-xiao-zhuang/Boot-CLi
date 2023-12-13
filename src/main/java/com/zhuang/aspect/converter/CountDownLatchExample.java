package com.zhuang.aspect.converter;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        // 创建并启动多个线程来执行任务
        for (int i = 0; i < taskCount; i++) {
            new Thread(() -> {
                System.out.println("Task " + Thread.currentThread().getId() + " is running.");
                try {
                    Thread.sleep((long) (Math.random() * 1000)); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + Thread.currentThread().getId() + " is completed.");
                countDownLatch.countDown(); // 任务完成，计数器减一
            }).start();
        }

        // 在主线程中等待所有任务完成
        countDownLatch.await(); // 等待计数器减为0
        System.out.println("All tasks are completed.");
    }
}
