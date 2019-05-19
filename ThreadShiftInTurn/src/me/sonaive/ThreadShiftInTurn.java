package me.sonaive;

import java.util.concurrent.Semaphore;

/**
 * 子线程循环 10 次，接着主线程循环 100 次，接着又回到子线程循环 10 次，接着再回到主线程又循环 100 次，如此循环50次
 * Created by liutao on 15/04/2019.
 */
public class ThreadShiftInTurn {

    private static final int MAIN_LOOP_COUNT = 100;
    private static final int CHILD_LOOP_COUNT = 10;
    private static final int TOTAL_LOOP_COUNT = 25;

    public static void main(String[] args) {
        Semaphore mainSem = new Semaphore(0);
        Semaphore childSem = new Semaphore(1);
        Thread thread = new Thread(new ShiftRunnable(childSem, mainSem));
        thread.start();
        try {
            for (int i = 0; i < TOTAL_LOOP_COUNT; i++) {
                mainSem.acquire();
                int j = 0;
                for (;j < MAIN_LOOP_COUNT; j++);
                System.out.println("main thread loops " + j);
                childSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class ShiftRunnable implements Runnable {

        private Semaphore semaphore;
        private Semaphore nextSemaphore;

        public ShiftRunnable(Semaphore semaphore, Semaphore nextSemaphore) {
            this.semaphore = semaphore;
            this.nextSemaphore = nextSemaphore;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < TOTAL_LOOP_COUNT; i++) {
                    semaphore.acquire();
                    int j = 0;
                    for (;j < CHILD_LOOP_COUNT; j++);
                    System.out.println("child thread loops " + j);
                    nextSemaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
