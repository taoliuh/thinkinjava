package com.sonaive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liutao on 1/6/16.
 */
public class MainThread {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            exec.execute(new SleepTask());
        }
        exec.shutdown();
    }
}
