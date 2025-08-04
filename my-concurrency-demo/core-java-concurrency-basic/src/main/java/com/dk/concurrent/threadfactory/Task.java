package com.dk.concurrent.threadfactory;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
