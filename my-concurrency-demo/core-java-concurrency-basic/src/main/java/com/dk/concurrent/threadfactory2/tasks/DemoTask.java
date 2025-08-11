package com.dk.concurrent.threadfactory2.tasks;

public class DemoTask implements Runnable {

    private final int id;

    public DemoTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - Executing DemoTask " + id);
        try {
            Thread.sleep(500); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
