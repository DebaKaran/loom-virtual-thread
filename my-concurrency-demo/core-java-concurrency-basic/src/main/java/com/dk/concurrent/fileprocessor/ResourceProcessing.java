package com.dk.concurrent.fileprocessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


public class ResourceProcessing implements Runnable {

    private BlockingQueue<String> queue;
    private String resourceName;
    private Map<String, Map<String, Integer>> globalMap;

    public ResourceProcessing(BlockingQueue<String> queue,
                              String resourceName,
                              Map<String, Map<String, Integer>> globalMap) {
        this.queue = queue;
        this.resourceName = resourceName;
        this.globalMap = globalMap;
    }

    @Override
    public void run() {
        Map<String, Integer> wordCount = new HashMap<>();
        System.out.println("Starting processor for " + resourceName);
        try {
            System.out.println("processing " + resourceName);
            while (true) {
                String line = queue.take();

                if (line.equals("__EOF__")) { // safer than "EOF"
                    globalMap.put(resourceName, wordCount);
                    System.out.println("End of processing " + resourceName);
                    return;
                }

                for (String word : line.trim().split("\\s+")) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
