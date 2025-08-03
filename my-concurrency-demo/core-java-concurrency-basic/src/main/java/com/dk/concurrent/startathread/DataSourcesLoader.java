package com.dk.concurrent.startathread;

import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable{

    @Override
    public void run() {
        System.out.println("Starting of datasource loading....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of datasource loading....");
    }
}
