package com.rwto.concurrent.basics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * interrupt() 中断线程，仅仅是将中断标志设置为true，并不是直接中断
 * 如果此线程内有使用wait(),sleep(),join()方法挂起线程，则抛出异常InterruptedException
 * isInterrupted() 检测调用线程是否被中断，中断为true,否则为false
 * interrupted()  这是一个静态方法！！获取当前线程的中断标志并重置，检测当前线程是否被中断，中断为true,但是会清掉中断标志，否则为false
 * @author renmw
 * @create 2023/11/30 9:29
 **/
@SpringBootTest
public class InterruptTest {

    @Test
    public void interruptTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + ":hello");
            }
        });

        thread.start();
        Thread.sleep(1);
        //中断线程：设置中断标记
        thread.interrupt();

        System.out.println("main is over");

    }

    @Test
    public void interruptedTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });

        thread.start();

        //设置中断标志
        thread.interrupt();

        System.out.println(""+thread.isInterrupted());//true

        //获取中断标准并重置，注意这里是静态方法，判断是当前线程的中断标志
        System.out.println(""+thread.interrupted());//false

        //获取中断标准并重置 与thread.interrupted() 效果一样
        System.out.println(""+Thread.interrupted());//false

        thread.join();
    }
}
