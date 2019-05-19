package me.sonaive;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liutao on 08/04/2019.
 */
public class SyncPrinter implements Runnable {

    private static final int PRINT_MAX_COUNT = 10;
    private final Lock lock;
    private final Condition thisCondition;
    private final Condition nextCondition;
    private final char printChar;

    public SyncPrinter(Lock lock, Condition thisCondition, Condition nextCondition, char printChar) {
        this.lock = lock;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < PRINT_MAX_COUNT; i++) {
                System.out.print(printChar);
                nextCondition.signalAll();
                if (i < PRINT_MAX_COUNT - 1) {
                    try {
                        thisCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Thread printerA = new Thread(new SyncPrinter(lock, conditionA, conditionB, 'A'));
        Thread printerB = new Thread(new SyncPrinter(lock, conditionB, conditionC, 'B'));
        Thread printerC = new Thread(new SyncPrinter(lock, conditionC, conditionA, 'C'));
        printerA.start();
        printerB.start();
        printerC.start();
    }
}
