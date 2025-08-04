package com.dk.concurrent.inheritablethreadlocal;

public class CustomInheritableThreadLocalDemo {
    static CustomInheritableThreadLocal context = new CustomInheritableThreadLocal();

    public static void main(String[] args) {
        context.set("Parent Context");

        Thread child = new Thread(() -> {
            System.out.println("Child Thread Context: " + context.get());
        });

        child.start();
    }
}
