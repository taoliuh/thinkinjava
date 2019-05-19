package com.sonaive;

import sun.tools.jconsole.Worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/8/16.
 */

class Task implements Runnable {

    @Override
    public void run() {
        NonTask.sleep();
        System.out.println("Task finished!");
    }
}

public class Interrupt {
    public static void main(String[] args) {
        // #1
        Thread t = new Thread(new Task());
        t.start();
        t.interrupt();
        // #2
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Task());
        exec.shutdownNow();
        // #3
        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        Future<?> f = exec2.submit(new Task());
        try {
            TimeUnit.MILLISECONDS.sleep(100); // start task
        } catch(InterruptedException e) {
            System.out.println("Sleep interrupted in main()");
        }
        f.cancel(true);
        exec2.shutdown();
    }
}
