package com.rwto.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * java线程的三种创建方式
 * @author renmw
 * @create 2023/11/28 11:18
 **/
public class CreateThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程
        MyThread thread = new MyThread();
        //启动线程，线程进入 就绪态，分配到cpu资源后进入运行态
        thread.start();

        //创建任务
        RunnableTask runnableTask = new RunnableTask();
        //启动线程
        new Thread(runnableTask).start();


        //创建任务
        CallableTask callableTask = new CallableTask();
        //启动线程
        FutureTask<String> future = new FutureTask<>(callableTask);
        new Thread(future).start();
        //获取返回结果
        System.out.println("这是线程执行结果："+future.get());

        System.out.println("这是主线程："+Thread.currentThread());
    }

    /**
     * 继承自Thread类，并重写run方法
     * 可直接获取线程的信息
     * 但是 任务和线程没有隔离，受单继承的限制，违背组合复用原则
     */
    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("这是一个线程(继承自Thread类)："+this);
        }
    }

    /**
     * 实现Runnable接口，并重写run方法
     * 任务与线程隔离，不同线程可以执行同一个任务
     * 结合lambda表达式可以省略类的创建
     */
    public static class RunnableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("这是一个线程(实现Runnable接口)："+this);
        }
    }

    /**
     * 实现Callable<>接口，并重写call方法
     * 任务与线程隔离，不同线程可以执行同一个任务
     * 并且可以获得线程的执行结果
     */
    public static class CallableTask implements Callable<String>{

        @Override
        public String call() {
            System.out.println("这是一个线程(实现Callable接口)："+this);
            return "${执行结果}";
        }
    }

}


