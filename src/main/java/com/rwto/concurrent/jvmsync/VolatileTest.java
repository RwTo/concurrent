package com.rwto.concurrent.jvmsync;

import java.util.concurrent.TimeUnit;

/**
 * volatile是Java虚拟机提供的轻量级的同步机制
 * 禁止指令重排。
 * 保证可见性。
 * 不保证原子性。（不同线程可能同时执行）
 *
 * 什么情况下使用volatile 关键字
 * 多线程更新共享变量 并使用当前变量做操作时
 */
class MyData{
     int num = 0;
    public void add(){
        num += 10;
    }
}
public class VolatileTest {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t进入");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName()+"\t修改为：" + myData.num);
        },"线程A").start();
        int count = 0;
        System.out.println(Thread.activeCount());
        /**读取到线程的工作内存中*/
        while (myData.num == 0){
            /**
             * 从线程自己的工作内存中读取，如果不更改，工作内存的数据一直是错误的
             */
            count += myData.num;
            //myData.add();
        }
        System.out.println(Thread.currentThread().getName() + "\t任务完成！");
    }
}
