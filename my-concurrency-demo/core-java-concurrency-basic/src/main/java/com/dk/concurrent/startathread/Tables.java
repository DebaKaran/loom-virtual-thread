package com.dk.concurrent.startathread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tables {

    private List<String> result;
    private Thread[] threads;
    private int numOfTables;

    public  Tables(int numOfTables) {
        this.numOfTables = numOfTables;

        //thread safe list
        this.result = Collections.synchronizedList(new ArrayList<>());
        this.threads = new MultiplicationThread[numOfTables];

        // Just initialize threads, don’t start them yet
        for(int num = 1; num <= numOfTables; num++) {
            threads[num - 1] = new MultiplicationThread(num, result);
        }
    }

    //Lazy Initilization
    private void generateTables() {
        for(Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // good practice
            }
        }
    }

    public List<String> getMultiplicationTable() {
        generateTables();
        return  result;
    }
}
