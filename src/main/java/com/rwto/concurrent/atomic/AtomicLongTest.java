package com.rwto.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author renmw
 * @create 2023/12/15 16:22
 **/
public class AtomicLongTest {

    /**
     * Long 原子类，通过 UnSafe 类中的CAS 非阻塞算法 操作完成 原子操作
     */
    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                /**
                 * 增加并获取增加后的值
                 * 内部是自旋CAS
                 * 在并发的情况下，可能会一直自旋，浪费CPU资源
                 */
                atomicLong.incrementAndGet();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                atomicLong.incrementAndGet();
            }
        }).start();
        System.out.println(Thread.activeCount());
        while (Thread.activeCount() > 1){
            Thread.yield();
        }

        System.out.println(atomicLong.get());
    }
}
