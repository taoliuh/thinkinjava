package com.sonaive;

import net.mindview.util.Countries;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by liutao on 1/28/16.
 */
public class IteratorTest {

    public static void iterator(List<String> list) {
        Iterator<String> iterator = list.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.print(next + ", ");
            if (i % 10  == 0) {
                System.out.println();
            }
            ++i;
        }
        System.out.println("\n------------------------------\n");
    }

    public static List<String> combineList(List<String> l1, List<String> l2) {
        if (l1 == null || l2 == null){
            return null;
        }
        ListIterator<String> it1 = l1.listIterator(l1.size());
        ListIterator<String> it2 = l2.listIterator();
        while (it1.hasPrevious() && it2.hasNext()) {
            it1.add(it2.next());
            it1.previous();
            it1.previous();
        }
        return l1;
    }

    public static void testAdd() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5"));
        ListIterator<String> iterator = list.listIterator(list.size());
        if (iterator.hasPrevious()) {
            iterator.add("6");
        }
        System.out.println(list);
        try {
            System.out.println("After add '6', iterator.next(): " + iterator.next());
        } catch (NoSuchElementException e) {
            System.out.println("After add '6', iterator.next(): NoSuchElementException");
        }
        System.out.println("After add '6', iterator.previous(): " + iterator.previous());

        ArrayList<String> array = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        ListIterator<String> arrIter = array.listIterator(array.size());
        if (arrIter.hasPrevious()) {
            arrIter.add("7");
        }
        System.out.println(array);
    }


    public static void main(String[] args) {
        List<String> names = Countries.names();
        ArrayList<String> arrayList = new ArrayList<>(names.subList(0, 15));
        LinkedList<String> linkedList = new LinkedList<>(names.subList(0, 5));
        iterator(arrayList);
        iterator(linkedList);
        testAdd();

        combineList(arrayList, linkedList);
        iterator(arrayList);
    }
}
