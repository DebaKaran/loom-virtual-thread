package com.dk.concurrent.threadlocal.with;

public class LoggerService {

    private final ThreadLocal<String> sessionId ;

    public LoggerService() {
        this.sessionId = new ThreadLocal<>();
    }

    public void setSessionId(String id) {
        sessionId.set(id);
    }

    public String getSessionId() {
        return sessionId == null ? "": sessionId.get();
    }

    public void log(String msg) {
        System.out.println("[" + sessionId.get() + "] " + msg);
    }

}
