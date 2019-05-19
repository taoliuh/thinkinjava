package com.sonaive;

/**
 * Created by liutao on 1/6/16.
 */
public class Task implements Runnable {
    private static int taskCount = 0;
    private final int id = taskCount++;
    int count = 3;

    public Task() {
        System.out.println(String.format("Task %d launched!", id));
    }

    public String status() {
        return "#" + id + "(" + count + ")";
    }

    @Override
    public void run() {
        while (count > 0) {
            System.out.println(status());
            count--;
            Thread.yield();
        }
        System.out.println(String.format("Task %d completed!", id));
    }
}
