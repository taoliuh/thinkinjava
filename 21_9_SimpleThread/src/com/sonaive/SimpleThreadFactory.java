package com.sonaive;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

/**
 * Created by liutao on 1/6/16.
 */
public class SimpleThreadFactory implements ThreadFactory {

    Random random = new Random();
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        int i = random.nextInt(3);
        switch (i) {
            case 0:
                t.setPriority(Thread.MIN_PRIORITY);
                break;
            case 1:
                t.setPriority(Thread.NORM_PRIORITY);
                break;
            case 2:
                t.setPriority(Thread.MAX_PRIORITY);
                break;
            default:
                break;
        }
        return t;
    }
}
