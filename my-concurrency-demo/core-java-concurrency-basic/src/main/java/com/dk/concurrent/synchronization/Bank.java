package com.dk.concurrent.synchronization;

public class Bank {
    public static void main(String[] args) {
        Account account = new Account(500);
        MoneyDepositor depositor = new MoneyDepositor(account);
        MoneyWithdrawer withdrawer = new MoneyWithdrawer(account);
        System.out.printf("Initial Balance: %.2f%n", account.getBalance());

        Thread depositorThread = new Thread(depositor);
        Thread withdrawerThread = new Thread(withdrawer);

        depositorThread.start();
        withdrawerThread.start();

        try {
            depositorThread.join();
            withdrawerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Final Balance: %.2f%n", account.getBalance());
    }
}
