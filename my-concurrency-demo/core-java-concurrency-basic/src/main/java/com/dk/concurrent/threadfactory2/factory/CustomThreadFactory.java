package com.dk.concurrent.threadfactory2.factory;

import com.dk.concurrent.threadfactory2.exceptions.MaxThreadException;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final int maxNumThreads;
    private final AtomicInteger count;

    public  CustomThreadFactory(int maxNumThreads) {
        this.maxNumThreads = maxNumThreads;
        this.count = new AtomicInteger(0);
    }

    @Override
    public Thread newThread(Runnable r) {
        if(count.get() >= maxNumThreads) {
            throw new MaxThreadException("Can't create thread beyond: "+maxNumThreads);
        }
        int threadNumber = count.incrementAndGet();
        String threadName = "Worker -:"+(threadNumber);
        System.out.println("Creating new thread: thread: "+threadName);
        return new Thread(r, threadName);
    }

    public int getCreatedCount() {
        return count.get();
    }

}
