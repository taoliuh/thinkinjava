package com.sonaive;

/**
 * Created by liutao on 2/20/16.
 */

class Base {
    int i;
    Base() {
        add(1);
        System.out.println(i);
    }

    void add(int v) {
        i += v;
        System.out.println("Base add: " + i);
    }
}

class MyBase extends Base {
    MyBase() {
        add(2);
    }

    void add(int v) {
        i += 2 * v;
        System.out.println("MyBase add: " + i);
    }
}


public class TestClu {
    public static void main(String[] args) {
        go(new MyBase());
    }

    static void go(Base b) {
        b.add(8);
    }
}
