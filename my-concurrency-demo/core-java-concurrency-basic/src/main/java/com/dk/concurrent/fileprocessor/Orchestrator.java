package com.dk.concurrent.fileprocessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Orchestrator {

    private final int numberofCores = Runtime.getRuntime().availableProcessors();

    private final ExecutorService executor = Executors.newFixedThreadPool(2 * numberofCores);

    public Map<String, Map<String, Integer>> orchestrate(String folderName) {
        final List<String> files = ResourceFileFinder.findFiles(folderName);

        if(files.isEmpty()) {
            return new ConcurrentHashMap<>();
        }
        // Global result map
        Map<String, Map<String, Integer>> globalMap = new ConcurrentHashMap<>();

        BlockingQueue<LineMessage> prevQueue = null;
        String prevFile = null;

        for(String file : files) {
            BlockingQueue<LineMessage> queue = new LinkedBlockingQueue<>();

            // Only for first time, we dont want ResourceProcessing to run
            //implcitly prevQueue will be also null when prevFile is null

            if(prevFile != null) {
             executor.submit(new ResourceProcessing(prevQueue, prevFile, globalMap));
            }

            executor.submit(new ResourceReader(file, queue));

            prevQueue = queue;
            prevFile = file;
        }

        //read the last file
        if(prevFile != null) {
            executor.submit(new ResourceProcessing(prevQueue, prevFile, globalMap));
        }

        executor.shutdown();
        terminate();
        return  globalMap;
    }

    private void terminate() {
        boolean terminated;
        try {
            terminated = executor.awaitTermination(10, TimeUnit.SECONDS);
            if (!terminated) {
                System.err.println("Executor did not terminate in the specified time.");
                executor.shutdownNow(); // optional: force shutdown
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // cancel currently executing tasks
            Thread.currentThread().interrupt(); // preserve interrupt status
            throw new RuntimeException("Executor was interrupted", e);
        }

    }
}
