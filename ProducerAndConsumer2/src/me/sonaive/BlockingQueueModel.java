package me.sonaive;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liutao on 27/02/2019.
 */
public class BlockingQueueModel implements Model {

    private final BlockingQueue<Item> mQueue;
    private final AtomicInteger mCount = new AtomicInteger(0);

    public BlockingQueueModel(int capacity) {
        mQueue = new LinkedBlockingQueue<>(capacity);
    }

    public static void main(String[] args) throws IOException {
        BlockingQueueModel model = new BlockingQueueModel(10);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(model.newProducerRunnable(i));
        }
        for (int i = 0; i < 3; i++) {
            executor.execute(model.newConsumerRunnable(i));
        }
        System.out.println("Press 'Enter' to quit.");
        System.in.read();
        executor.shutdownNow();
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
            Thread.sleep((long) (Math.random() * 1000));
            Item item = new Item(mCount.incrementAndGet());
            mQueue.put(item);
            System.out.println("Producer-" + id + ", produce: " + item.id);
        }
    }

    private class ConsumerImpl extends AbstractConsumer {

        public ConsumerImpl(int id) {
            super(id);
        }

        @Override
        public void consume() throws InterruptedException {
            Item item = mQueue.take();
            Thread.sleep((long) (500 + Math.random() * 500));
            System.out.println("Consumer-" + id + ", consume: " + item.id);
        }

    }
}
