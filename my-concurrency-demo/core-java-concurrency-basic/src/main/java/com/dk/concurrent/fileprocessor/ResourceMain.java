package com.dk.concurrent.fileprocessor;

import java.util.Map;

public class ResourceMain {
    public static void main(String[] args) {
        Orchestrator orchestrator = new Orchestrator();
        Map<String, Map<String, Integer>> result = orchestrator.orchestrate("data");
        for (String fileName: result.keySet()) {
            Map<String, Integer> wordCount = result.get(fileName);
            System.out.println("FileName: "+fileName);
            for (String word : wordCount.keySet()) {
                System.out.println("Word: "+word+" and count is: "+wordCount.get(word));
            }
        }
    }
}
