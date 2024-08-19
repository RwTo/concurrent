package com.rwto.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author renmw
 * @create 2024/4/16 11:25
 * countDownLatch  用来做同步阻塞
 * 可以实现一个线程或多个线程一直等待
 * 原理：内部一个计数器，初始化使给计数器一个初始值，
 * await方法会阻塞当前线程，直到其他线程调用该类的countDown方法，当计数器为0 时，
 * 所有因为await等待的线程释放
 **/
@Slf4j
public class CountDownLatchTest {
    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //countDownLatch.await();
        countDownLatch.await(1, TimeUnit.SECONDS);
        log.info("finish");
        exec.shutdown();

    }
    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}", threadNum);
    }
}
