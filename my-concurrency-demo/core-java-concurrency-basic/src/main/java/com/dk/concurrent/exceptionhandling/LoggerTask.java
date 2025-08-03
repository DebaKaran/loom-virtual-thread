package com.dk.concurrent.exceptionhandling;

public class LoggerTask implements Runnable{
    private int count;

    public LoggerTask(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Message: Log generated at: "+System.currentTimeMillis());
        int value = 1 /count;
        System.out.println("Value is: "+value);
    }
}
