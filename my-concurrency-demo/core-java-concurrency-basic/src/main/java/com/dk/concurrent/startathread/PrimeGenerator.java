package com.dk.concurrent.startathread;

public class PrimeGenerator implements Runnable
{
    @Override
    public void run() {
        long num = 1L;
        boolean shouldRun = true;

        while (shouldRun) {
            if(isPrime(num)) {
                System.out.println("Pirme Number: "+num);
            }
            if(Thread.currentThread().isInterrupted()) {
                System.out.printf("The prime generator has been interrupted");
                shouldRun = false;
            }
            num++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 1L) return false;
        if (number == 2L) return true;
        if(number % 2 == 0) return  false;

        long value = (long)(Math.sqrt(number));

        for(long num = 3; num <= value; num += 2) {
            if(number %  num == 0) return  false;
        }

        return true;
    }
}
