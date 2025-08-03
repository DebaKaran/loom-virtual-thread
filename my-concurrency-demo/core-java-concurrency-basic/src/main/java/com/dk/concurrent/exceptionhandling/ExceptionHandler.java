package com.dk.concurrent.exceptionhandling;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("Thread: %s\n",t.getName());
        System.out.printf("Exception: %s: %s\n",e.getClass().
                getName(),e.getMessage());
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n",t.getState());
    }
}
