package com.rwto.concurrent.basics;

/**
 * volatile 关键字
 * 可以确保  volatile 写之前的操作不会被编译器重新排序到后面
 * 可以确保  volatile 读之后的操作不会被编译器重新排序到前面
 */

/**
 * @author renmw
 * @create 2023/12/13 17:13
 **/
public class instructionRearrangementTest {

    private static int num = 0;
    private volatile static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                if(ready){
                    System.out.println(num+num);
                }
                //System.out.println("read Thread ....");
            }
        });
        readThread.start();

        /**
         * 单线程没有指令重排的问题
         * jvm 允许编译器和处理器对指令进行排序以提高允许性能
         * 但只会对没有数据依赖的指令进行重排序
         * 例：a = 2 ; b = 3 ; c = a + b;
         * a 和 b 没有数据依赖，可能被指令重排
         * 微观：a = b;
         * 可以拆分成三个动作，
         * 1. 开辟a 的内存空间，
         * 2. a 的指针指向该空间
         * 3. b 的值复制到该空间
         * 2 和 3 没有数据依赖，可以重排
         *
         * volatile 关键字可以防止指令重排
         */
        Thread writeThread = new Thread(() -> {
            num = 2;
            ready = true;
            System.out.println("write Thread ....");
        });


        writeThread.start();
        Thread.sleep(10);

        readThread.interrupt();
    }
}
