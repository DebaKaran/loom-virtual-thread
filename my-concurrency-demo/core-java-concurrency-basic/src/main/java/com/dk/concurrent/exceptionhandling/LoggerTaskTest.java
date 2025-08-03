package com.dk.concurrent.exceptionhandling;

import java.util.concurrent.TimeUnit;

public class LoggerTaskTest {

    public static void main(String[] args) {
        System.out.println("Starting: "+Thread.currentThread().getName());
        for(int i = 0; i < 2; i++) {
            LoggerTask task = new LoggerTask(i);
            Thread loggerThread = new Thread(task, "LoggerThread-"+i);
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
