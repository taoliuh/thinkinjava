package com.sonaive;

import java.util.Arrays;

/**
 * Created by liutao on 1/20/16.
 */
public class UnsortedArrayBinarySearch {
    public static void main(String[] args) {
        int[] a = {3, 5, 2, 9, 1, 11, 9};
        int i = Arrays.binarySearch(a, 1);
        System.out.printf("Index is %d\n", i);
    }
}
