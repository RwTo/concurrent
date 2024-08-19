package com.rwto.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author renmw
 * @create 2024/4/16 13:24
 * 用于线程间的相互等待，比如必须达到设置的值才能继续进行
 * 可以reset 将计数器重置，
 * 计数器是+1的
 * countDownLatch 描述 一个或多个线程等待其他线程
 * CyclicBarrier 描述线程间的互相等待
 **/
@Slf4j
public class CyclicBarrierTest {
    //达到屏障后执行的回调方法
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{log.info("callable");});

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} continue", threadNum);
    }
}
