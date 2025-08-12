package com.dk.concurrent.synchronization;

public class MoneyWithdrawer implements Runnable{
    private Account account;

    public MoneyWithdrawer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 30; i++) {
            account.withdrawAmount(50);
        }
    }
}
