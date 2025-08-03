package com.dk.concurrent.threadlocal.without;

public class LoggerServiceTest {
    public static void main(String[] args) {
        LoggerService service = new LoggerService();

        Thread thread1 = new Thread(service);
        service.setSessionId("session-1");

        Thread thread2 = new Thread(service);
        service.setSessionId("session-2");

        thread1.start();
        thread2.start();
    }
}
