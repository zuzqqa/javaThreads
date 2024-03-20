package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class ResultQueue {
    private final Queue<Integer> queue;

    {
        queue = new LinkedList<>();
    }

    public synchronized void addResult(int result){
        queue.add(result);
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int poll() {
        return queue.poll();
    }
}

