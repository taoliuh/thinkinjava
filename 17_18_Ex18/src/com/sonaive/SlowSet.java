package com.sonaive;

import java.util.*;

/**
 * Created by liutao on 2/16/16.
 */
public class SlowSet<E> extends AbstractSet<E> {

    private List<E> values = new ArrayList<>();

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = -1;

            @Override
            public boolean hasNext() {
                return index < values.size() - 1;
            }

            @Override
            public E next() {
                return values.get(++index);
            }

            @Override
            public void remove() {
                values.remove(index);
            }
        };
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean add(E e) {
        if (!values.contains(e)) {
            values.add(e);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SlowSet<String> ss = new SlowSet<>();
        ss.add("hi");
        System.out.println(ss);
        ss.add("there");
        System.out.println(ss);
        List<String> list = Arrays.asList("you", "cutie", "pie");
        ss.addAll(list);
        System.out.println(ss);
        System.out.println("ss.size() = " + ss.size());
        System.out.println("ss.contains(\"you\"): " + ss.contains("you"));
        System.out.println("ss.contains(\"me\"): " + ss.contains("me"));
        System.out.println("ss.containsAll(list): " + ss.containsAll(list));
        SlowSet<String> ss2 = new SlowSet<>();
        System.out.println("ss2 = " + ss2);
        System.out.println("ss.containsAll(ss2): " + ss.containsAll(ss2));
        System.out.println("ss2.containAll(ss): " + ss2.containsAll(ss));
        ss2.add("you");
        ss2.add("cutie");
        ss.removeAll(ss2);
        System.out.println("ss = " + ss);
        System.out.println("ss.hashCode() = " + ss.hashCode());
        List<String> list2 = Arrays.asList("hi", "there", "pie");
        ss2.remove("you");
        System.out.println(ss2);
        System.out.println("ss2.isEmpty(): " + ss2.isEmpty());
        ss2.clear();
        System.out.println("ss2.isEmpty(): " + ss2.isEmpty());
        ss2.addAll(list2);
        System.out.println("ss2 = " + ss2);
        System.out.println("ss.equals(ss2): " + ss.equals(ss2));
        String[] sa = new String[3];
        System.out.println("ss.toArray(sa): " + ss.toArray(sa));
        for(int i = 0; i < sa.length; i++) System.out.print(sa[i] + " " );
    }
}
