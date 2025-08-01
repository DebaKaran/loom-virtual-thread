package com.dk.concurrent.startathread;

public class MultiplicationTest {
    public static void main(String[] args) {
        Thread[] threads =  new MultiplicationThread[10];

        for(int num = 1; num <= 10; num++) {
            threads[num - 1] = new MultiplicationThread(num);
            threads[num - 1].start();
        }

    }
}
