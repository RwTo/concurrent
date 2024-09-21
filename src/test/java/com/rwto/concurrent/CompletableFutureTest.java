package com.rwto.concurrent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

/**
 * @author renmw
 * @create 2024/8/18 14:05
 **/
@SpringBootTest
public class CompletableFutureTest {

    @Test
    void contextLoads() {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(()->{
            return 2;
        });

    }

}
