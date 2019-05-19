package com.sonaive;

import java.util.Arrays;
import java.util.Comparator;

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

    @Override
    public String toString() {
        return "Equals " + i;
    }

    public int get() {
        return i;
    }
}

class EqualsComparator implements Comparator<Equals> {

    @Override
    public int compare(Equals o1, Equals o2) {
        return o1.get() - o2.get();
    }
}

public class Ex24 {
    public static void main(String[] args) {
        Equals[] e = new Equals[10];
        for (int i = 0; i < e.length; i++) {
            e[i] = new Equals(i);
        }
        System.out.println(Arrays.toString(e));
        System.out.println(Arrays.binarySearch(e, new Equals(5), new EqualsComparator()));
    }
}
