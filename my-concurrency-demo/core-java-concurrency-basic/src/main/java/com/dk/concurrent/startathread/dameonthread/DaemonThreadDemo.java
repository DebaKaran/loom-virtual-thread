package com.dk.concurrent.startathread.dameonthread;

import java.util.concurrent.TimeUnit;

public class DaemonThreadDemo {
    public static void main(String[] args) {
        Orchestrator orchestrator = new Orchestrator();
        orchestrator.orchestrate(3); // Launch 3 writer threads

        try {
            TimeUnit.SECONDS.sleep(5);// Keep main thread alive for a while
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End of "+Thread.currentThread().getName());
    }
}
