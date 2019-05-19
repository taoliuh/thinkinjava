package com.sonaive;

import java.util.Arrays;

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
}
public class Ex18 {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 10; i++) {
            spheres[i] = new BerylliumSphere();
        }
        BerylliumSphere[] copy = new BerylliumSphere[10];
        System.arraycopy(spheres, 0, copy, 0, spheres.length);
        System.out.println(Arrays.toString(spheres));
        System.out.println(Arrays.toString(copy));
    }
}
