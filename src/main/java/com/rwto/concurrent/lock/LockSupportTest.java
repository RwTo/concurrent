package com.rwto.concurrent.lock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport的主要作用是挂起和唤醒线程
 * 创建锁和其他同步类的基础
 * 内部是使用UnSafe 实现的
 * @author renmw
 * @create 2023/12/18 18:54
 **/
@SpringBootTest
public class LockSupportTest {



    public static void main(String[] args) {
        System.out.println("begin");
        LockSupport.parkNanos(2000000000);

        System.out.println("end");
    }

    @Test
    public void parkTest(){
        System.out.println("begin");
        /**
         * 使当前线程获取许可证
         */
        LockSupport.unpark(Thread.currentThread());
        /**
         * 如果没有LockSupport的许可证
         * 线程会被阻塞挂起
         * 注意：线程被设置中断标记后，park方法会正常返回，同样有虚假唤醒的风险
         * LockSupport.parkNanos(); 多个超时时间 纳秒
         */
        //LockSupport.parkNanos(1000000000);
        LockSupport.park();

        System.out.println("end");

    }

    @Test
    public void parkTest2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("child begin");
            LockSupport.park();
            System.out.println("child end");
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("unpark");
        LockSupport.unpark(thread);
    }


    @Test
    public void parkTest3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("child begin");
            /**
             * 防止虚假唤醒！
             */
            while (!Thread.currentThread().isInterrupted()){
                LockSupport.park();
            }
            System.out.println("child end");
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("interrupt");
        thread.interrupt();
    }
}

/**
 * 先进先出锁
 */
class FIFOMutex{
    private final AtomicBoolean locked = new AtomicBoolean(false);

    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

    public  void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        //如果不是队首或者当前锁已经被其他线程获取，则挂起当前线程
        while(waiters.peek() != current || !locked.compareAndSet(false,true)){
            LockSupport.park(this);
            if(Thread.interrupted()){//防止因为中断导致返回
                //当前线程已中断，但清除中断标志
                wasInterrupted = true;
            }
        }

        waiters.remove();
        if (wasInterrupted){
            //如果线程被中断过，则恢复中断线程，防止影响其他线程需要这个中断标记
            current.interrupt();
        }
    }

    public  void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}

