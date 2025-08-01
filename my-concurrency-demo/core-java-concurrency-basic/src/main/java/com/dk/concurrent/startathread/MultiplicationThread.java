package com.dk.concurrent.startathread;

import java.util.List;

public class MultiplicationThread extends Thread{

    private final int num;
    private final List<String> result;

    public MultiplicationThread(int num, List<String> result) {
        this.num = num;
        this.result = result;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            result.add("Thread-"+Thread.currentThread().getName()+": "+num+" * "+i+" = "+(num * i));
        }
    }
}
