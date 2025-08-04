package com.dk.concurrent.threadlocal.executor.withoutremoval;

public class LoggerService {

    private final ThreadLocal<String> sessionId ;

    public LoggerService() {
        this.sessionId = new ThreadLocal<>();
    }

    public void setSessionId(String id) {
        // Simulating a large object
        byte[] largeData = new byte[10 * 1024 * 1024]; // 10 MB
        sessionId.set(id + "-" + largeData.hashCode());  // Attach large data
    }

    public void log(String msg) {
        System.out.println("[" + sessionId.get() + "] " + msg);
    }

}
