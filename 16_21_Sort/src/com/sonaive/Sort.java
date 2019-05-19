package com.sonaive;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by liutao on 1/20/16.
 */

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }

    public long getId() {
        return id;
    }
}

class ReverseComparator implements Comparator<BerylliumSphere> {

    @Override
    public int compare(BerylliumSphere o1, BerylliumSphere o2) {
        return (int) (o2.getId() - o1.getId());
    }
}

public class Sort {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 10; i++) {
            spheres[i] = new BerylliumSphere();
        }
        Arrays.sort(spheres, new ReverseComparator());
        System.out.println(Arrays.toString(spheres));
    }
}
