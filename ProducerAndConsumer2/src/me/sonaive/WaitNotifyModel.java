package me.sonaive;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liutao on 27/02/2019.
 */
public class WaitNotifyModel implements Model {

    private final Queue<Item> mQueue = new LinkedList<>();
    private final Object mLock = new Object();
    private final int mCapacity;
    private final AtomicInteger mCount = new AtomicInteger(0);

    public WaitNotifyModel(int capacity) {
        mCapacity = capacity;
    }

    public static void main(String[] args) throws IOException {
        WaitNotifyModel model = new WaitNotifyModel(10);
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executors.execute(model.newProducerRunnable(i));
        }
        for (int i = 0; i < 3; i++) {
            executors.execute(model.newConsumerRunnable(i));
        }
        System.out.println("Press 'Enter' to quit.");
        System.in.read();
        executors.shutdownNow();
    }

    @Override
    public Runnable newProducerRunnable(int id) {
        return new ProducerImpl(id);
    }

    @Override
    public Runnable newConsumerRunnable(int id) {
        return new ConsumerImpl(id);
    }

    private class ProducerImpl extends AbstractProducer {

        public ProducerImpl(int id) {
            super(id);
        }

        @Override
        public void produce() throws InterruptedException {
            synchronized (mLock) {
                while (mQueue.size() == mCapacity) {
                    mLock.wait();
                }
                Thread.sleep((long) (Math.random() * 1000));
                Item item = new Item(mCount.incrementAndGet());
                mQueue.offer(item);
                System.out.println("Producer-" + id + ", produce " + item.id);
                mLock.notifyAll();
            }
        }
    }

    private class ConsumerImpl extends AbstractConsumer {

        public ConsumerImpl(int id) {
            super(id);
        }

        @Override
        public void consume() throws InterruptedException {
            synchronized (mLock) {
                while (mQueue.size() == 0) {
                    mLock.wait();
                }
                Thread.sleep(500 + (long) (Math.random() * 500));
                Item item = mQueue.poll();
                System.out.println("Consumer-" + id + ", consume " + item.id);
                mLock.notifyAll();
            }
        }
    }
}
