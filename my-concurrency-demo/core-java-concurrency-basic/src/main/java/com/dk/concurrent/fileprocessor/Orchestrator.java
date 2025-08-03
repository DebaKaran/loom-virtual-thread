package com.dk.concurrent.fileprocessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Orchestrator {

    private final int N_CPU_CORES = Runtime.getRuntime().availableProcessors();

    private final ExecutorService cpuPool = Executors.newFixedThreadPool(N_CPU_CORES);
    private final ExecutorService ioPool = Executors.newCachedThreadPool();


    public Map<String, Map<String, Integer>> orchestrate(String folderName) {
        final List<String> files = ResourceFileFinder.findFiles(folderName);

        if(files.isEmpty()) {
            return new ConcurrentHashMap<>();
        }
        // Global result map
        Map<String, Map<String, Integer>> globalMap = new ConcurrentHashMap<>();


        for(String file : files) {
            BlockingQueue<LineMessage> queue = new LinkedBlockingQueue<>();
            ioPool.submit(new ResourceReader(file, queue));
            cpuPool.submit(new ResourceProcessing(queue, file, globalMap));
        }

        //read the last file

        cpuPool.shutdown();
        ioPool.shutdown();
        terminate(cpuPool);
        terminate(ioPool);
        return  globalMap;
    }

    private void terminate(ExecutorService service) {
        boolean terminated;
        try {
            terminated = service.awaitTermination(10, TimeUnit.SECONDS);
            if (!terminated) {
                System.err.println("Executor did not terminate in the specified time.");
                service.shutdownNow(); // optional: force shutdown
            }
        } catch (InterruptedException e) {
            service.shutdownNow(); // cancel currently executing tasks
            Thread.currentThread().interrupt(); // preserve interrupt status
            throw new RuntimeException("Executor was interrupted", e);
        }

    }
}
