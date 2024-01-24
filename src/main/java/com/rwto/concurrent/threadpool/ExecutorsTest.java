package com.rwto.concurrent.threadpool;

import java.util.concurrent.Executors;

/**
 * @author renmw
 * @create 2024/1/24 15:32
 **/
public class ExecutorsTest {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(1);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
    }
}
