package com.rwto.concurrent.basics;

import lombok.AllArgsConstructor;

/**
 * java 的内存模型规定：
 * 将所有的变量豆放在主内存中，当线程使用变量时，会把主内存里面的变量复制到自己的工作空间，也就是工作内存，对应cpu寄存器或核内一级缓存
 * 线程读写变量时是操作的自己工作内存中的变量
 * */
/**
 * synchronized 是java 内置一种原子性的内置锁，也就是监视器锁
 * 此内置锁是排他锁。
 */

/**
 * @author renmw
 * @create 2023/12/13 15:34
 **/
public class VolatileTest {
    public static void main(String[] args) {
        AtomicVolatileInteger atomicInteger = new AtomicVolatileInteger(0);
        new Thread(()->{
            for (int j = 0; j < 100000; j++) {
                syncBlock(atomicInteger);
            }
        }).start();

        new Thread(()->{
            for (int j = 0; j < 100000; j++) {
                syncBlock(atomicInteger);
            }
        }).start();
    }

    public static void syncBlock(AtomicVolatileInteger atomicInteger){
        System.out.println(Thread.currentThread()+"i:"+atomicInteger.num);
        atomicInteger.num++;
    }


}

@AllArgsConstructor
class AtomicVolatileInteger{
    /**
     * 只解决内存可见性问题，不能实现同步
     * 被声明volatile的字段，直接从主内存中读写，解决可见性问题shi
     * volatile 不保证 原子性，例如：依赖于初始值进行计算时，原子操作为：读取-计算-写入，使用 volatile 无法解决并发问题
     */
    public volatile Integer num;
}

/**
 * 什么时候使用volatile 关键字？
 * 1. 写入变量
 *
 * */