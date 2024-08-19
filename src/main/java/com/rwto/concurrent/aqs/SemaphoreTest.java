package com.rwto.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author renmw
 * @create 2024/4/16 13:16
 * 用于控制同一时间并发线程的数目，控制某个资源可以被同时访问的个数——限流
 **/
@Slf4j
public class SemaphoreTest {
    private static final int threadCount = 200;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(); //获取一个许可
                    //semaphore.acquire(3);//获取多个许可
                    test(threadNum);
                    semaphore.release(); //释放一个许可
                    //semaphore.release(3); //释放多个许可

                    /*
                    //尝试获取一个许可，也可以尝试获取多个许可，
                    //支持尝试获取许可超时设置，超时后不再等待后续线程的执行
                    if(semaphore.tryAcquire()){
                        test(threadNum);
                        semaphore.release();
                    }*/

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        new Thread().start();
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
