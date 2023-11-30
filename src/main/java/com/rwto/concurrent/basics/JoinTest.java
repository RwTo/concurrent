package com.rwto.concurrent.basics;

/**
 * join()方法，Thread类的方法
 * 阻塞当前线程，等待调用线程执行结束
 * @author renmw
 * @create 2023/11/28 18:05
 **/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+" : "+i);
            }
        });
        thread.start();
        /**只是打上中断标记，并不会中断线程*/
        thread.interrupt();
        thread.join();

        System.out.println("主线程执行完毕！");
    }
}
