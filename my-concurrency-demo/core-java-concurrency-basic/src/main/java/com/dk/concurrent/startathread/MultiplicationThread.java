package com.dk.concurrent.startathread;

public class MultiplicationThread extends Thread{

    private int num;

    public MultiplicationThread(int num) {
        this.num = num;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Thread-"+Thread.currentThread().getName()+": "+num+" * "+i+" = "+(num * i));
        }
    }
}
