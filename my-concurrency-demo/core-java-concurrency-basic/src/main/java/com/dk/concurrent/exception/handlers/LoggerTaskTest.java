package com.dk.concurrent.exception.handlers;

import java.util.concurrent.TimeUnit;

public class LoggerTaskTest {

    public static void main(String[] args) {
        System.out.println("Starting: "+Thread.currentThread().getName());
        // Set once globally
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        for(int i = 0; i < 3; i++) {
            LoggerTask task = new LoggerTask(i);
            Thread loggerThread = new Thread(task, "LoggerThread-"+i);
            if(i == 1) {
                loggerThread.setUncaughtExceptionHandler(new NullPointerHandler());
            }
            // No handler set for i == 0 → fallback to global handler
            loggerThread.start();

        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ending: "+Thread.currentThread().getName());

    }
}
