package com.sonaive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/8/16.
 */
public class Ex21 {

    static class TaskA implements Runnable {

        private volatile boolean signal = false;

        private synchronized void waitForA() throws InterruptedException{
            while (!signal) {
                wait();
                signal = true;
            }
        }

        @Override
        public void run() {
            try {
                waitForA();
            } catch (InterruptedException e) {
                System.out.println("Task A interrupted!");
            }
            System.out.println("Task A finished!");
        }
    }

    static class TaskB implements Runnable {

        private TaskA task;

        public TaskB(TaskA task) {
            this.task = task;
        }

        private void notifyTaskA() {
            synchronized (task) {
                task.notifyAll();
            }
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                notifyTaskA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        TaskA taskA = new TaskA();
        exec.execute(taskA);
        exec.execute(new TaskB(taskA));
        exec.shutdown();
    }
}
