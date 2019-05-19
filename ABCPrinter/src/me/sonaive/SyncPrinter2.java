package me.sonaive;

import java.util.concurrent.Semaphore;

/**
 * Created by liutao on 08/04/2019.
 */
public class SyncPrinter2 implements Runnable {
    private static final int PRINT_MAX_COUNT = 10;
    private final Semaphore thisSem;
    private final Semaphore nextSem;
    private final char printChar;

    public SyncPrinter2(Semaphore thisSem, Semaphore nextSem, char printChar) {
        this.thisSem = thisSem;
        this.nextSem = nextSem;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < PRINT_MAX_COUNT; i++) {
                thisSem.acquire();
                System.out.print(printChar);
                nextSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Semaphore semA = new Semaphore(1);
        Semaphore semB = new Semaphore(0);
        Semaphore semC = new Semaphore(0);
        Thread printerA = new Thread(new SyncPrinter2(semA, semB, 'A'));
        Thread printerB = new Thread(new SyncPrinter2(semB, semC, 'B'));
        Thread printerC = new Thread(new SyncPrinter2(semC, semA, 'C'));
        printerC.start();
        printerB.start();
        printerA.start();
    }
}
