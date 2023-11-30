package com.rwto.concurrent.basics;

/**
 * sleep() Thread 类的方法
 * 使当前线程睡眠*ms,不释放锁
 * @author renmw
 * @create 2023/11/28 18:27
 **/
public class SleepTest {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread()+":准备执行！");
            synchronized (SleepTest.class){
                System.out.println(Thread.currentThread()+":拿到锁开始执行！");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+":执行完毕！");
            }
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread()+":准备执行！");
            synchronized (SleepTest.class){
                System.out.println(Thread.currentThread()+":拿到锁开始执行！");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+":执行完毕！");
            }
        }).start();
    }
}
