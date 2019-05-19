package com.sonaive;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by liutao on 1/26/16.
 */
public class Ex1 {
    public static void main(String[] args) {
        List<String> names = Countries.names();
        ArrayList<String> al = new ArrayList<>(names);
        LinkedList<String> ll = new LinkedList<>(names);
        System.out.println(al);
        System.out.println(ll);
        Collections.shuffle(al);
        System.out.println("Shuffle arraylist:");
        System.out.println(al);

        Collections.shuffle(ll, new Random(47));
        System.out.println("Shuffle linkedlist:");
        System.out.println(ll);
    }
}
