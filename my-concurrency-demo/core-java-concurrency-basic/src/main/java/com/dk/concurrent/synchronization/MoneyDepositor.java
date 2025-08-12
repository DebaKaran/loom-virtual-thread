package com.dk.concurrent.synchronization;

public class MoneyDepositor implements Runnable{
    private Account account;

    public MoneyDepositor(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 30; i++) {
            account.addAmount(100);
        }
    }
}
