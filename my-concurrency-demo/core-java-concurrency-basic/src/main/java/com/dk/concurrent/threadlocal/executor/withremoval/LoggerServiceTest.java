package com.dk.concurrent.threadlocal.executor.withremoval;

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
                // Not calling service.removeSessionId();
                // So ThreadLocal keeps holding old large data in the thread's map
            });
            if (i % 10_000 == 0) {
                System.out.println("Submitted: " + i);
                try {
                    Thread.sleep(100); // Let GC catch up slowly
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        executorService.shutdown();
        System.out.println("All tasks submitted");
    }
}
