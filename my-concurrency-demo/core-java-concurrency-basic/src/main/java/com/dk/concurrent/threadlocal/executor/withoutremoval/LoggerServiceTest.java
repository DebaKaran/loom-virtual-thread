package com.dk.concurrent.threadlocal.executor.withoutremoval;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoggerServiceTest {

    public static void main(String[] args) throws InterruptedException {
        LoggerService service = new LoggerService();
        ExecutorService executor = Executors.newFixedThreadPool(2); // small pool
        try {
            for (int i = 1; i <= 100_000; i++) {
                final int id = i;

                executor.submit(() -> {
                    // Simulate large memory object per session
                    String largeSessionId = "session-" + id;
                    service.setSessionId(largeSessionId);
                    service.log("Task " + id);

                    // Not calling service.removeSessionId();
                    // So ThreadLocal keeps holding old large data in the thread's map
                });

                if (i % 10_000 == 0) {
                    System.out.println("Submitted: " + i);
                    Thread.sleep(100); // Let GC catch up slowly
                }
            }
        } finally {
            executor.shutdown();
        }
        System.out.println("All tasks submitted");
    }
}
