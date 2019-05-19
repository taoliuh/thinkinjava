package com.sonaive;

/**
 * Created by liutao on 1/6/16.
 */
public class FibonacciTask implements Runnable {

    private int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    private long fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++) {
            System.out.print(fib(i) + " ");
        }
    }
}
