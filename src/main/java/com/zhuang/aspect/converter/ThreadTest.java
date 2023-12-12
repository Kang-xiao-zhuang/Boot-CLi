package com.zhuang.aspect.converter;

public class ThreadTest {
    static volatile boolean  stop = false;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
            stop = true;
            System.out.println(Thread.currentThread().getName() + " modify to true");
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(500);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + stop);
        }, "t2").start();

        new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("stopped... " + i);
        }, "t3").start();
    }
}
