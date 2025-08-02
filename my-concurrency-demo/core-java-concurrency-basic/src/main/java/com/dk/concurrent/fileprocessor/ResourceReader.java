package com.dk.concurrent.fileprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.BlockingQueue;

public class ResourceReader implements Runnable {
    private String resourceName;
    private BlockingQueue<String> queue ;

    public ResourceReader(String resourceName, BlockingQueue<String> queue) {
        this.resourceName = resourceName;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Simulate IO delay
        System.out.println("Starting Reading for " + resourceName + " and it will take 4 seconds...");
        try {
            Thread.sleep(Duration.ofMillis(4000));
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
           return;
        }
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(resourceName)){
            if (input == null) {
                System.err.println("Resource not found: " + resourceName);
                return;
            }

            InputStreamReader streamReader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(streamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                queue.put(line);
            }

            queue.put("__EOF__"); // signal end of file
            System.out.println("Reading for "+resourceName+" is completed");
        } catch (IOException e) {
            throw new RuntimeException("Error reading " + resourceName, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
