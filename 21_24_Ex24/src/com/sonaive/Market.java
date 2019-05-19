package com.sonaive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liutao on 1/9/16.
 */

class Item {
    private final int id;

    public Item(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item " + id;
    }
}

class Producer implements Runnable {

    private int count;
    Market market;

    public Producer(Market market) {
        this.market = market;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                while (count < 100) {
                    Item item = new Item(++count);
                    if (market.storage.offer(item)) {
                        System.out.println("Produced Item " + count);
                        synchronized (market.consumer) {
                            market.consumer.notifyAll();
                        }
                    }
                    synchronized (this) {
                        while (market.storage.size() >= 10) {
                            wait();
                        }
                    }
                }
                // Stop production after 100 Items:
                System.out.println("Produced " + count + " Items"
                        + "\nStopping production");
                market.exec.shutdownNow();
            } catch(InterruptedException e) {
                System.out.println("Producer interrupted");
                System.out.println("Produced " + count + " Items");
            }
        }
    }
}

class Consumer implements Runnable {

    private int consumed = 0;
    private Market market;
    private List<Item> cart = new ArrayList<>();

    public Consumer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                synchronized (this) {
                    while (cart.size() >= market.producer.getCount()) {
                        wait();
                    }
                }
                if(cart.add(market.storage.poll())) {
                    System.out.println("Consuming Item " + ++consumed);
                    // Notify producer - ready for more:
                    synchronized(market.producer) {
                        market.producer.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
                System.out.println("Consumed " + consumed + " Items");
            }
        }
    }
}

public class Market {
    public Producer producer = new Producer(this);
    public Consumer consumer = new Consumer(this);
    public Queue<Item> storage = new LinkedList<>();
    public ExecutorService exec = Executors.newCachedThreadPool();

    public Market() {
        exec.execute(producer);
        exec.execute(consumer);
        exec.shutdown();
    }

    public static void main(String[] args) {
        new Market();
    }
}
