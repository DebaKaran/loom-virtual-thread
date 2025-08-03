package com.dk.concurrent.startathread;

import java.util.concurrent.TimeUnit;

public class PrimeGeneratorTest {
    public static void main(String[] args) {
        Thread primeGeneratorThread = new Thread(new PrimeGenerator());
        primeGeneratorThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        primeGeneratorThread.interrupt();
    }
}
