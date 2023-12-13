package com.rwto.concurrent.safety;

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
public class SynchronizedTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
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

    /**
     * 增加synchronized ，同步进行增加
     * 因为是排他锁，同时也解决了内存可见性的问题，同一时间只有一个线程操作此同步块
     * synchronized 保证原子性操作，可以用来解决线程安全问题
     * @param atomicInteger
     */
    public synchronized static void syncBlock(AtomicInteger atomicInteger){
        System.out.println(Thread.currentThread()+"i:"+atomicInteger.num);
        atomicInteger.num++;
    }


}
@AllArgsConstructor
class AtomicInteger{
    public Integer num;
}
