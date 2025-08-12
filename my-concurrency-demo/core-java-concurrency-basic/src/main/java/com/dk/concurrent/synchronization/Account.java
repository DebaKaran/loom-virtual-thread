package com.dk.concurrent.synchronization;

import java.util.concurrent.TimeUnit;

public class Account {

    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void addAmount(double amount) {
        System.out.println(Thread.currentThread().getName() + " trying to add " + amount);
        double temp = balance;
        System.out.println(Thread.currentThread().getName() + " read balance: " + temp);

        temp += amount;

        try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) {}
        balance = temp;
        System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
    }

    public synchronized void withdrawAmount(double amount) {
        System.out.println(Thread.currentThread().getName() + " trying to withdraw " + amount);
        double temp = balance;
        System.out.println(Thread.currentThread().getName() + " read balance: " + temp);

        temp -= amount;

        try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) {}
        balance = temp;
        System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
    }

    public synchronized double getBalance() {
        return balance;
    }
}
