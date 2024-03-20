package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    Queue<Integer> taskQueue;

    {
        taskQueue = new LinkedList<>();
    }

    public synchronized void addTask(int task) {
        taskQueue.add(task);
        notify();
    }

    public synchronized int getNextTask() throws InterruptedException {
        while (taskQueue.isEmpty()) {
            wait();
        }
        return taskQueue.poll();
    }

    public synchronized boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
