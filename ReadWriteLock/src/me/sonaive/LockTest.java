package me.sonaive;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 16/04/2019.
 */
public class LockTest {

    ExecutorService exec = Executors.newCachedThreadPool();
    private static final int SIZE = 100;
    private static Random rand = new Random(47);
    private ReadWriterList<Integer> list = new ReadWriterList<>(SIZE, 0);

    private class Writer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    list.set(i, rand.nextInt());
                    TimeUnit.MICROSECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit;
            }
            System.out.println("Writer finished, shutting down");
        }
    }

    private class Reader implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < SIZE; i++) {
                    list.get(i);
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                // Acceptable way to exit;
            }
        }
    }

    public LockTest(int readers, int writers) {
        for (int i = 0; i < readers; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < writers; i++) {
            exec.execute(new Writer());
        }
        try {
            TimeUnit.SECONDS.sleep(10);
            exec.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
