package com.dk.concurrent.threadlocal.executor.withoutremoval;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoggerServiceTest {
    public static void main(String[] args) {
        LoggerService service = new LoggerService();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i = 1; i <= 1_000_000; i++) {
            final int val = i;
            executorService.submit(() -> {
               service.setSessionId("Session - "+val);
               service.log("Task "+val);
                // NOTE: No remove() here -> memory retained in ThreadLocal map
            });
        }
        executorService.shutdown();
    }
}
