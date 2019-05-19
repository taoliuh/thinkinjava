package com.sonaive;

import net.mindview.util.Countries;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by liutao on 1/26/16.
 */
public class Ex3 {
    public static void main(String[] args) {
        List<String> names = Countries.names(10);
        HashSet<String> hashSet = new HashSet<>(names);
        LinkedHashSet<String> hset = new LinkedHashSet<>(names);
        TreeSet<String> tset = new TreeSet<>(names);
        System.out.println(hashSet);
        System.out.println(hset);
        System.out.println(tset);
        List<String> moreNames = Countries.names(15);
        hashSet.addAll(moreNames);
        hset.addAll(moreNames);
        tset.addAll(moreNames);
        System.out.println(hashSet);
        System.out.println(hset);
        System.out.println(tset);
    }
}
