package com.dk.concurrent.threadlocal.without;

public class LoggerService implements Runnable{

    private String sessionId;

    @Override
    public void run() {
        System.out.println("Session ID: " + sessionId);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
