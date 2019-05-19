package com.sonaive;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liutao on 1/13/16.
 */
public class FixedDiningPhilosopher {
    public static void main(String[] args) throws Exception {
        int ponder = 0;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        BlockingQueue<Chopstick> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < size; i++) {
            Chopstick c = new Chopstick(i);
            queue.add(c);
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(i, queue, ponder));
        }
        System.out.println("Press 'Enter' to quit.");
        System.in.read();
        exec.shutdownNow();
    }
}
