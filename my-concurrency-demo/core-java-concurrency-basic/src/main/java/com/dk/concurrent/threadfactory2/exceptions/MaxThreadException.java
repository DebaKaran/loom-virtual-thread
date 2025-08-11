package com.dk.concurrent.threadfactory2.exceptions;

public class MaxThreadException extends RuntimeException{
    public MaxThreadException() {
        super();
    }
    public MaxThreadException(String s) {
        super(s);
    }

}
