package com.sonaive;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/13/16.
 */
public class Philosopher implements Runnable {

    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);
    private BlockingQueue<Chopstick> queue;

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(int id, BlockingQueue<Chopstick> queue, int ponderFactor) {
        this.id = id;
        this.queue = queue;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();
                System.out.println(this + " grabbing left");
                Chopstick left = queue.take();
                System.out.println(this + " grabbing right");
                Chopstick right = queue.take();
                System.out.println(this + " eating");
                pause();
                queue.put(right);
                queue.put(left);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
