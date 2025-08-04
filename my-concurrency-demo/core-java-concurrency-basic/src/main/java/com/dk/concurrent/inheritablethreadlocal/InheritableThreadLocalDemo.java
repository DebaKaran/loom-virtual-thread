package com.dk.concurrent.inheritablethreadlocal;

public class InheritableThreadLocalDemo {
    static InheritableThreadLocal<String> context = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        context.set("Parent Thread Value");

        Thread child = new Thread(() -> {
                System.out.println("Child Thread Inherited: " + context.get());
        });
        child.start();
    }
}
