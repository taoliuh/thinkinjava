package com.sonaive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 12/31/15.
 */
public class FixedInfiniteRecursion {
    public String toString() {
        return "FixedInfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<FixedInfiniteRecursion> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new FixedInfiniteRecursion());
        }
        System.out.println(list);
    }
}
