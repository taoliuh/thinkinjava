package com.sonaive;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/12/16.
 */

class ToastSandwich {
    public enum Status {
        DRY,
        PEANUT_BUTTERED,
        JELLIED;
    }
    private Status status = Status.DRY;
    private final int id;
    public ToastSandwich(int idn) {
        id = idn;
    }
    public void peanutButter() {
        status = Status.PEANUT_BUTTERED;
    }

    public void jelly() {
        status = Status.JELLIED;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ToastSandwich " + id + ": " + status;
    }
}

class Toaster implements Runnable {
    ToastQueue queue;
    int count = 0;
    Random random = new Random(47);
    public Toaster(ToastQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                ToastSandwich t = new ToastSandwich(count++);
                System.out.println(t);
                queue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted!");
        }
        System.out.println("Toast off!");
    }
}

class ToastQueue extends LinkedBlockingQueue<ToastSandwich> {}

class Butterer implements Runnable {

    ToastQueue dryQueue;
    ToastQueue butteredQueue;

    public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ToastSandwich t = dryQueue.take();
                t.peanutButter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted!");
        }
        System.out.println("Butterer off!");
    }
}

class Jelly implements Runnable {

    ToastQueue butteredQueue;
    ToastQueue jellyQueue;

    public Jelly(ToastQueue butteredQueue, ToastQueue jellyQueue) {
        this.butteredQueue = butteredQueue;
        this.jellyQueue = jellyQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ToastSandwich t = butteredQueue.take();
                t.jelly();
                System.out.println(t);
                jellyQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jelly interrupted!");
        }
        System.out.println("Jellied off!");
    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;
    public Eater(ToastQueue queue) {
        this.finishedQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ToastSandwich t = finishedQueue.take();
                if (t.getId() != counter++ || t.getStatus() != ToastSandwich.Status.JELLIED) {
                    System.err.println("Error " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted!");
        }
        System.out.println("Eater off!");
    }
}

public class ToastOMatic {
    public static void main(String[] args) {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butteredQueue = new ToastQueue();
        ToastQueue finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jelly(butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdownNow();
    }
}
