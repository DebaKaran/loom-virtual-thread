package com.dk.concurrent.threadfactory2.tasks;

public class FailingTask implements Runnable{
    private final int id;

    public FailingTask(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - Executing FailingTask " + id);
        throw new RuntimeException("Simulated crash in FailingTask " + id);
    }
}
