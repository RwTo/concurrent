package com.rwto.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 根据锁只能被单个线程持有还是能被 个线程共同持有，锁可以分为独占锁（排他锁）和共享锁
 * ReentrantLock 是独占锁，同一时间只能被一个线程占有
 * ReadWriteLock 是共享锁，同一时间不同线程可以有多个读锁，都可以读取数据
 * 共享锁是一种乐观锁
 * 独占锁（排他锁）是一种悲观锁
 * @author renmw
 * @create 2023/12/13 22:43
 **/
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantLock exclusiveLock = new ReentrantLock();
        ReadWriteLock sharedLock = new ReentrantReadWriteLock();
    }
}
