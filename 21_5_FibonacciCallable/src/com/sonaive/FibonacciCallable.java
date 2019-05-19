package com.sonaive;

import java.util.concurrent.Callable;

/**
 * Created by liutao on 1/6/16.
 */
public class FibonacciCallable implements Callable<Long[]> {

    private int n;

    public FibonacciCallable(int n) {
        this.n = n;
        System.out.println("Compute fib(" + n + ")");
    }

    private long fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public Long[] call() throws Exception {
        Long[] result = new Long[n];
        for (int i = 1; i <= n; i++) {
            result[i - 1] = fib(i);
        }
        return result;
    }
}
