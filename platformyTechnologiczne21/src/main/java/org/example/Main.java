package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedResource taskQueue = new SharedResource();
        ResultQueue resultQueue = new ResultQueue();
        int numberOfThreads = Integer.parseInt(args[0]);
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new IsPrimeRunnable(taskQueue, resultQueue, i));
            threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a number (or 'end' to finish): ");
            synchronized (scanner) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("end")) {
                    break;
                }
                taskQueue.addTask(Integer.parseInt(input));
            }
        }

        int i = 0;

        for(Thread thread: threads){
            thread.interrupt();
            System.out.println("Thread number: " + i + " was interrupted.\n");
            thread.join();
            i++;
        }

        System.out.println("Results: ");

        while(!resultQueue.isEmpty()){
            System.out.println(resultQueue.poll());
        }

        scanner.close();
    }
}
