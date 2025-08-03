package com.dk.concurrent.startathread.dameonthread;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Orchestrator {

    public void orchestrate(int numOfWriterTasks) {
        Deque<Event> queue = new ConcurrentLinkedDeque<>();

        //create number of writer task
        for(int i = 1; i<= numOfWriterTasks; i++) {
            WriterTask task = new WriterTask(queue);
            Thread thread = new Thread(task);
            thread.start();
        }

        CleanerTask cleanerTask = new CleanerTask(queue);
        Thread cleanerThread = new Thread(cleanerTask);
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }
}
