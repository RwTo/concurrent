package com.rwto.concurrent.basics;

import java.util.LinkedList;
import java.util.Queue;

/**
 * wait()方法，Object类的方法
 * 使用wait() 前，要先获取 共享变量的监视器锁，即必须在synchronized中使用，否则抛出 illegalMonitorStateException
 * 使用wait() 后，线程会被挂起，进入阻塞状态，并切释放当前锁
 * 可以使用notify() 和 notifyAll() 唤醒线程
 * @author renmw
 * @create 2023/11/28 15:38
 **/
public class WaitTest {


    private static Integer MAX_SIZE = 1;
    private static final Queue<String> queue = new LinkedList<>();
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                produce(String.valueOf(i));
            }
        },"生产者A").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                produce(String.valueOf(i));
            }
        },"生产者B").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                consume();
            }
        },"消费者A").start();


        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                consume();
            }
        },"消费者B").start();

    }

    private static void produce(String product) {
        synchronized (queue){
            System.out.println(Thread.currentThread().getName()+" 准备生产产品！"+" 当前产品总数："+queue.size());
            //线程唤醒后进入while循环再判断一下条件，这里只能使用while，如果使用if 会出现虚假唤醒
            /**虚假唤醒
             * 线程争夺到锁后，不满足执行条件，应该睡眠，但却正常执行了
             * */
            while(queue.size() == MAX_SIZE){
                try {
                    //使用wait() 前，要先获取 共享变量的监视器锁，即必须在synchronized中使用，否则抛出 illegalMonitorStateException
                    //使用wait() 后，线程会被挂起，进入阻塞状态，并切释放当前锁
                    //可以使用notify() 和 notifyAll() 唤醒线程
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            queue.offer(product);
            System.out.println(Thread.currentThread().getName()+" 生产产品："+product +" 剩余产品总数："+queue.size());
            //queue.notify() //随机唤醒一个阻塞线程，使被唤醒的线程进入就绪状态
            queue.notifyAll();//唤醒对象queue的所有阻塞线程，使被唤醒的线程进入就绪状态
        }
    }

    private static void consume() {
        synchronized (queue){
            System.out.println(Thread.currentThread().getName()+" 准备消费产品！"+" 当前产品总数："+queue.size());
            while(queue.size() == 0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String product = queue.poll();
            System.out.println(Thread.currentThread().getName()+" 消费产品："+product +" 剩余产品总数："+queue.size());
            queue.notifyAll();
        }
    }
}