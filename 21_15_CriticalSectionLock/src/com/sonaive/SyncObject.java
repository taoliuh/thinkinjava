package com.sonaive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class A {
    public synchronized void a() {
        for (int i = 0; i < 5; i++) {
            System.out.println("a()");
            Thread.yield();
        }
    }

    public synchronized void b() {
        for (int i = 0; i < 5; i++) {
            System.out.println("b()");
            Thread.yield();
        }
    }

    public synchronized void c() {
        for (int i = 0; i < 5; i++) {
            System.out.println("c()");
            Thread.yield();
        }
    }
}

class TaskA implements Runnable {
    final A a;
    public TaskA(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.a();
    }
}

class TaskB implements Runnable {
    final A a;
    public TaskB(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.b();
    }
}

class TaskC implements Runnable {
    final A a;
    public TaskC(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.c();
    }
}

public class SyncObject {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        A a = new A();
        exec.execute(new TaskA(a));
        exec.execute(new TaskB(a));
        exec.execute(new TaskC(a));
        exec.shutdown();
    }
}
