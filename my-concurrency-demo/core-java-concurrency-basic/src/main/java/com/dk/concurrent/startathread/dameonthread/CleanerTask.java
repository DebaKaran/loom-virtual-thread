package com.dk.concurrent.startathread.dameonthread;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class CleanerTask implements Runnable {

    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {

        while (true) { // Keep cleaning as long as JVM is alive
            Event event = deque.peekLast();

            while ((event != null && ((System.currentTimeMillis() - event.getTime()) / 1000) > 3)) {
                deque.removeLast();
                System.out.println("Cleaner removed: " + event.getMsg());
                event = deque.peekLast();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(300); // Give CPU breathing room
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
