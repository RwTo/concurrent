package com.rwto.concurrent.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author renmw
 * 32@create 2024/4/15 17:33
 **/
public class SimpleDateFormatTest {
    //执行总次数
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 20;
    //SimpleDateFormat对象
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        simpleDateFormat.parse("2020-01-01");
                    } catch (ParseException | NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + "格式化日期失败");
                                e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }


}
