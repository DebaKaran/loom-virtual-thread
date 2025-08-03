package com.dk.concurrent.startathread;

import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable{
    @Override
    public void run() {
        System.out.println("Starting of network connection....");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of network connection....");
    }
}
