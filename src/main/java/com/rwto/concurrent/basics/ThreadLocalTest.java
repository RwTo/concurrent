package com.rwto.concurrent.basics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

/**
 * 通过Thread线程类的参数 threadLocals 实现线程隔离
 * threadLocals 是一个 ThreadLocalMap map结构，key是ThreadLocal对象，value 为泛型
 * 因为是map结构，所以同一个线程可以有多个ThreadLocal对象，存多组数据
 *
 * 在ThreadLocal 不使用后一定要调用Remove 删除map中的对象，不然会出现内存泄露
 * @author renmw
 * @create 2023/12/6 22:46
 **/
@SpringBootTest
public class ThreadLocalTest {
    private static ThreadLocal<String> local = new ThreadLocal<>();
    private static ThreadLocal<String> local2 = new InheritableThreadLocal<>();

    @Test
    public void test01(String[] args) {
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

    /**
     * ThreadLocal 子线程不能访问父线程的ThreadLocal
     */
    @Test
    public void test02() throws InterruptedException {
        local.set("hello world！");
        new Thread(()->{
            System.out.println("子线程："+local.get());
        }).start();
        System.out.println("父线程："+local.get());

        Thread.sleep(1000);
        local.remove();

    }

    /**
     * InheritableThreadLocal  是ThreadLocal的子类
     * 子线程可以访问父线程的ThreadLocal
     */
    @Test
    public void test03() throws InterruptedException {
        local2.set("hello world！");
        new Thread(()->{
            System.out.println("子线程："+local2.get());
        }).start();
        System.out.println("父线程："+local2.get());

        Thread.sleep(1000);
        local2.remove();

    }
}
