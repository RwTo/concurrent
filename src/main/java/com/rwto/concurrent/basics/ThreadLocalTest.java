package com.rwto.concurrent.basics;

import java.util.stream.LongStream;

/**
 * @author renmw
 * @create 2023/12/6 22:46
 **/
public class ThreadLocalTest {
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            LongStream.range(1,10).forEach(index->{
                local.set("线程1");
                System.out.println(Thread.currentThread()+"before remove : "+local.get());
                local.remove();
                System.out.println(Thread.currentThread()+"after remove : " +local.get());
            });

        }).start();

        new Thread(()->{
            LongStream.range(1,10).forEach(index ->{
                local.set("线程2");
                System.out.println(Thread.currentThread()+"before remove : "+local.get());
                local.remove();
                System.out.println(Thread.currentThread()+"after remove : " +local.get());
            });

        }).start();

        LongStream.range(1,10).forEach(index->{
            local.set("main线程");
            System.out.println(Thread.currentThread()+"before remove : "+local.get());
            local.remove();
            System.out.println(Thread.currentThread()+"after remove : " +local.get());
        });

    }
}
