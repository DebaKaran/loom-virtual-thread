package com.dk.concurrent.startathread;

public class InitializationCoordinator {
    public static void main(String[] args) {
        Thread dbThread = new Thread(new DataSourcesLoader());
        Thread networkThread = new Thread(new NetworkConnectionsLoader());

        long start = System.currentTimeMillis();

        dbThread.start();
        networkThread.start();

        try {
            dbThread.join();
            networkThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + " ms");
    }
}
