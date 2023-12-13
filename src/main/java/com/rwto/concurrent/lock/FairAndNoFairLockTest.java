package com.rwto.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 根据线程获取锁的抢占先机，分为公平锁和非公平锁
 * 1. 公平锁： 线程获取锁的顺序是 按照线程请求锁的时间早晚来决定的，公平锁会带来性能开销
 * 2. 非公平锁： 会在运行时闯入争夺锁，也就是说先来不一定先得
 *
 * @author renmw
 * @create 2023/12/13 21:57
 **/
public class FairAndNoFairLockTest {
    public static void main(String[] args) {
        /*非公平锁*/
        ReentrantLock noFairLock = new ReentrantLock();
        ReentrantLock noFairLock1 = new ReentrantLock(false);

        /*公平锁：会带来额外性能开销*/
        ReentrantLock fairLock = new ReentrantLock(true);
    }
}
