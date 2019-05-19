package com.sonaive;

import net.mindview.util.RandomGenerator;

import java.util.TreeSet;

/**
 * Created by liutao on 2/2/16.
 */
public class Ex9 {
    public static void main(String[] args) {
        RandomGenerator.String random = new RandomGenerator.String(10);
        TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(random.next());
        }
        System.out.println(set);
    }
}
