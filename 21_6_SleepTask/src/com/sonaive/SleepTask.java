package com.sonaive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/6/16.
 */
public class SleepTask implements Runnable {

    private static int taskCount = 0;
    private final int id = taskCount++;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        int sleepTime = random.nextInt(1000);
        System.out.printf("Thread %d, Sleep %d ms", id, sleepTime);
        System.out.println();
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
