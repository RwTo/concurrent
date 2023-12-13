package com.rwto.concurrent.lock;

/**
 * 可重入锁：已经加锁的代码块可以再次获取已获得的锁，可以防止自己死锁
 * synchronized 本身就是一个可重入锁
 * ReentrantLock 也是可重入锁
 * @author renmw
 * @create 2023/12/13 22:56
 **/
public class ReentrantLockTest {
    public static void main(String[] args) {
        /**
         * 可重入锁的原理：
         * 在锁内部维护一个线程标识，标识这个锁属于哪个线程
         * 并关联一个计数器，初始为0，每获取一次锁，计数器+1，释放则-1
         * 当计数器为0 时，线程标识被重置为null，其他线程就可以争抢锁了
         */
        new ReentrantLockTest().helloA();
    }

    public synchronized void helloA(){
        System.out.println("hello A");
        helloB();
    }

    public synchronized void helloB(){
        System.out.println("hello B");
    }
}
