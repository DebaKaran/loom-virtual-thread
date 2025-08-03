package com.dk.concurrent.exception.handlers;

public class NullPointerHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!(e instanceof NullPointerException)) {
            return;
        }
        System.out.println("NullPointerHandler caught: " + e);
    }
}
