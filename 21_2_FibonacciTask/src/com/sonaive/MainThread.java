package com.sonaive;

/**
 * Created by liutao on 1/6/16.
 */
public class MainThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new FibonacciTask(30));
        thread.start();
    }
}
