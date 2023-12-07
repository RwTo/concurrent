package com.rwto.concurrent.basics;

/**
 * 守护线程和用户线程
 * 最后一个用户线程结束后，jvm就会退出，无论守护线程是否结束
 * @author renmw
 * @create 2023/12/6 22:36
 **/
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(true);
        });
        //设置为守护线程
        thread.setDaemon(true);
        thread.start();
        //最后一个用户线程结束后，jvm就会退出，无论守护线程是否结束;
        System.out.println("main is over");
        /**
         * 默认情况下，tomcat 用于接收用户连接 和 处理请求的线程 都是守护线程
         * 也就是说在接收到shutdown 命令后，tomcat停止完最后一个用户线程，无论接收线程和处理线程有没有结束
         * tomcat 都停止运行
         * */
    }
}
