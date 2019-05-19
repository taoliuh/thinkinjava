package me.sonaive;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liutao on 08/03/2019.
 */

public class ProducerAndConsumer {

    private static Queue<Item> mQueue = new LinkedList<>();
    private static AtomicInteger mCount = new AtomicInteger(0);
    private static final Object mLock = new Object();

    public static void main(String[] args) throws IOException {
        ExecutorService executors = Executors.newCachedThreadPool();
        int i;
        for (i = 0; i < 5; i++) {
            executors.execute(new Producer(i));
        }
        for (i = 0; i < 3; i++) {
            executors.execute(new Consumer(i));
        }
        System.in.read();
        executors.shutdownNow();
    }

    private static class Item {
        int id;

        public Item(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Item-" + id;
        }
    }

    private static class Producer implements Runnable {

        int id;

        public Producer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    synchronized (mLock) {
                        while (mQueue.size() >= 10) {
                            mLock.wait();
                        }
                        Thread.sleep((long) (Math.random() * 500));
                        Item item = new Item(mCount.incrementAndGet());
                        System.out.println("Producer-" + id + " produces " + item);
                        mQueue.offer(item);
                        mLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Producer-" + id + " interrupted!");
            }

        }
    }

    private static class Consumer implements Runnable {

        int id;

        public Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    synchronized (mLock) {
                        while (mQueue.size() == 0) {
                            mLock.wait();
                        }
                        Thread.sleep((long) (500 + Math.random() * 500));
                        Item item = mQueue.poll();
                        System.out.println("Consumer-" + id + " consumes " + item);
                        mLock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer-" + id + " interrupted!");
            }

        }
    }
}
