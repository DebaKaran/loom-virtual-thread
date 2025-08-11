package com.dk.concurrent.threadfactory2.runner;

import com.dk.concurrent.threadfactory2.factory.CustomThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner {
    ThreadPoolExecutor threadPoolExecutor;
    private final AtomicInteger restartsTriggered;
    private final AtomicInteger threadsCreated;

    public TaskRunner() {
        this.restartsTriggered =  new AtomicInteger(0);
        this.threadsCreated = new AtomicInteger(0); // track via factory
        int cores = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        CustomThreadFactory factory = new CustomThreadFactory(2 * cores) {
            @Override
            public Thread newThread(Runnable r) {
                threadsCreated.incrementAndGet();
                return super.newThread(r);
            }
        };
        this.threadPoolExecutor = new ThreadPoolExecutor(cores,
                2 * cores,
                0L,
                TimeUnit.MILLISECONDS,
                queue,
                factory
                );
    }

    public void submitTask(Runnable task) {
        threadPoolExecutor.submit(task);
    }
    public void shutdown() {
        threadPoolExecutor.shutdown();
    }
    public void incrementRestarts() {
        restartsTriggered.incrementAndGet();
    }
    public String getTask() {
        return String.format( "Threads created: %d, Tasks completed: %d, Restarts triggered: %d",
                ((CustomThreadFactory)threadPoolExecutor.getThreadFactory()).getCreatedCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                restartsTriggered.get()
        );
    }

}