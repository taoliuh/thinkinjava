package com.sonaive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liutao on 1/6/16.
 */
public class MainThread {

    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Long[]>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new FibonacciCallable(random.nextInt(40))));
        }
        for (Future<Long[]> f : results) {
            System.out.println(f.toString() + " is Done: " + f.isDone());
            try {
                System.out.println(Arrays.toString(f.get()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }
    }
}
