package com.dk.concurrent.startathread.dameonthread;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask implements Runnable{

    private final Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for(int times= 0; times < 5; times++) {
            Event event = new Event();
            deque.addFirst(event);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
