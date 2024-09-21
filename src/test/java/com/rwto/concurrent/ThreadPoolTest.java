package com.rwto.concurrent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * @author renmw
 * @create 2024/9/19 22:25
 **/
@SpringBootTest
public class ThreadPoolTest {

    @Test
    public void test01(){
        LinkedBlockingDeque<Object> blockingDeque = new LinkedBlockingDeque<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4,
                0l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        executor.execute(()->{
            System.out.println("1 "+Thread.currentThread());
        });

        executor.execute(()->{
            System.out.println("2 "+Thread.currentThread());
        });

        //这里核心线程执行任务会抛异常，在finally块中会添加一个非核心线程去补偿执行任务，如果没有任务就会消失

        executor.execute(()->{

            System.out.println("3 "+Thread.currentThread());
            throw  new NullPointerException();
        });

        executor.execute(()->{
            System.out.println("4 "+Thread.currentThread());
        });


        /*不再接受新的任务，等待任务执行完释放线程*/
        /*通过设置中断标记，中断take和poll的阻塞状态*/
        //executor.shutdown();
        /*不在接受任务，当前任务中断*/
        //executor.shutdownNow();
        while (true){

        }
    }
}
