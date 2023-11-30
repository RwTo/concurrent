package com.rwto.concurrent.basics;

/**
 * yield() Thread类的 方法
 * 释放当前时间片，然后处于就绪状态
 * 放弃当前CPU的调度权，进入就绪态，等下次获取到调度权再继续执行
 * 不会释放锁
 * @author renmw
 * @create 2023/11/28 19:31
 **/
public class YieldTest {
    public static void main(String[] args) {
        startThread();

        startThread();

        startThread();

        startThread();

        startThread();

        startThread();

        startThread();

    }

    private static void startThread() {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if((i%10) == 0){
                    System.out.println(Thread.currentThread()+"释放cpu");
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread()+"执行完毕！");
        }).start();
    }
}
