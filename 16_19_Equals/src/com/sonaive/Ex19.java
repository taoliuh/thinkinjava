package com.sonaive;

import java.util.Arrays;

/**
 * Created by liutao on 1/20/16.
 */

class Equals {
    private int i;
    public Equals(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        Equals other = (Equals) obj;
        return this.i == other.i;
    }
}

public class Ex19 {
    public static void main(String[] args) {
        Equals[] e1 = new Equals[10];
        Equals[] e2 = new Equals[10];
        Arrays.fill(e1, new Equals(0));
        Arrays.fill(e2, new Equals(0));
        System.out.println(Arrays.equals(e1, e2));
    }
}
