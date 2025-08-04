package com.dk.concurrent.threadlocal.with;

public class LoggerServiceTest {
    public static void main(String[] args) {
        LoggerService service = new LoggerService();

        Thread thread1 = new Thread(() -> {
            service.setSessionId("session-1");
            service.log("Thread 1");
        });


        Thread thread2 = new Thread(() -> {
            service.setSessionId("session-2");
            service.log("Thread 2");
        });

        thread1.start();
        thread2.start();


    }
}
