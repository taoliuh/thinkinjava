package com.sonaive;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by liutao on 2/2/16.
 */

class SortedSet<E> extends LinkedList<E> {

    int compare(E e1, E e2) {
        int c = e1.hashCode() - e2.hashCode();
        return c < 0 ? -1 : (c == 0 ? 0 : 1);
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        int index = 0;
        ListIterator<E> iterator = listIterator();
        while (iterator.hasNext()) {
            if (compare(iterator.next(), e) < 1) {
                ++index;
            }
        }
        super.add(index, e);
        return true;
    }
}

class T {}

public class Ex10 {
    public static void main(String[] args) {
        SortedSet<T> ss = new SortedSet<T>();
        ss.add(new T());
        ss.add(new T());
        ss.add(new T());
        ss.add(new T());
        System.out.println(ss);
        SortedSet<Integer> ss2 = new SortedSet<Integer>();
        ss2.add(6);
        ss2.add(8);
        ss2.add(2);
        ss2.add(4);
        ss2.add(2);
        ss2.add(8);
        System.out.println(ss2);
        SortedSet<String> ss3 = new SortedSet<String>();
        ss3.add("three");
        ss3.add("three");
        ss3.add("seven");
        ss3.add("hi");
        ss3.add("two");
        ss3.add("one");
        System.out.println(ss3);
    }
}
