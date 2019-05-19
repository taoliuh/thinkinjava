package me.sonaive;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liutao on 26/02/2019.
 */
public class Market {

    ExecutorService mExecutor = Executors.newCachedThreadPool();

    public Market() {
        Goods goods = new Goods();
        for (int i = 0; i < 10; i++) {
            mExecutor.execute(new Producer(this, i, goods));
        }
        for (int i = 0; i < 5; i++) {
            mExecutor.execute(new Consumer(i, goods));
        }
        mExecutor.shutdown();
    }

    public static void main(String[] args) {
        new Market();
    }

}

class Item {
    private final int mId;

    public Item(int id) {
        mId = id;
    }

    @Override
    public String toString() {
        return "item + " + mId;
    }
}

class Producer implements Runnable {

    private Market mMarket;
    private Goods mList;
    private final int mId;

    public Producer(Market market, int id, Goods list) {
        mMarket = market;
        mId = id;
        mList = list;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                synchronized (mList) {
                    while (mList.mProducedCount < 1000) {
                        while (mList.mStorage.size() >= 10) {
                            mList.wait();
                        }
                        Item item = new Item(++mList.mProducedCount);
                        if (mList.mStorage.offer(item)) {
                            System.out.println("Producer-" + mId + " produced Item " + mList.mProducedCount);
                            mList.notifyAll();
                        }
                    }
                }
                mMarket.mExecutor.shutdownNow();
            } catch(InterruptedException e) {
                System.out.println("Producer-" +  mId + ", interrupted");
            }
        }
    }
}

class Consumer implements Runnable {

    private Goods mList;
    private final int mId;

    public Consumer(int id, Goods list) {
        mId = id;
        mList = list;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                synchronized (mList) {
                    while (mList.mConsumedCount >= mList.mProducedCount) {
                        mList.wait();
                    }
                    if(mList.mStorage.poll() != null) {
                        ++mList.mConsumedCount;
                        System.out.println("Consumer-" + mId + ", Consuming Item " + mList.mConsumedCount);
                        // Notify producer - ready for more:
                        mList.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer-" + mId + " interrupted");
            }
        }
    }
}

class Goods {
    // 仓库存的货物
    public Queue<Item> mStorage = new LinkedList<>();
    // 已消费的货物数量
    public int mConsumedCount = 0;
    // 已生产的货物数量
    public int mProducedCount = 0;

}
