package com.dk.concurrent.startathread.dameonthread;

public class Event {

    private final long timeinMills;
    private final String msg;

    public Event() {
        this.timeinMills = System.currentTimeMillis();
        this.msg = "Thread " + Thread.currentThread().getName() +
                " generated an event at: " + timeinMills;
    }

    public long getTime() {
        return timeinMills;
    }

    public String getMsg() {
        return msg;
    }
}
