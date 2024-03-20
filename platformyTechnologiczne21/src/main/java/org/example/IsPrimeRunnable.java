package org.example;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class IsPrimeRunnable implements Runnable{
    private final SharedResource taskQueue;
    private final ResultQueue resultQueue;
    public final int threadNumber;

    public IsPrimeRunnable(SharedResource taskQueue, ResultQueue resultQueue, int i){
        this.taskQueue = taskQueue;
        this.resultQueue = resultQueue;
        this.threadNumber = i;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (!taskQueue.isEmpty()) {
                    int number = taskQueue.getNextTask();
                    TimeUnit.MILLISECONDS.sleep(number);
                    // System.out.println(threadNumber + "delayed: " + number);
                    System.out.println("\n------Thread " + threadNumber + " processing task: " + number + "------\n");
                    if (isPrime(number)) {
                        resultQueue.addResult(number);
                        System.out.println("------Thread " + threadNumber + " found prime: " + number + "------\n");
                    }
                    else {
                        System.out.println("------Thread " + threadNumber + " found non-prime: " + number + "------\n");

                    }
                }
            }
        } catch (InterruptedException e) {}
    }
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}