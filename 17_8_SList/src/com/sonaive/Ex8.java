package com.sonaive;

/**
 * Created by liutao on 2/2/16.
 */
public class Ex8 {
    public static void main(String[] args) {
        SList<String> sl = new SList<>();
        System.out.println(sl);
        SListIterator<String> slIter = sl.iterator();
        System.out.println("inserting \"hi\"");
        slIter.add("hi");
        System.out.println(sl);
        System.out.println("inserting \"there\"");
        slIter.add("there");
        System.out.println(sl);
        System.out.println("inserting \"sweetie\"");
        slIter.add("sweetie");
        System.out.println(sl);
        System.out.println("inserting \"pie\"");
        slIter.add("pie");
        System.out.println(sl);
        SListIterator<String> slIter2 = sl.iterator();
        System.out.println("removing \"hi\"");
        slIter2.remove();
        System.out.println(sl);
        System.out.println("inserting \"hello\"");
        slIter2.add("hello");
        System.out.println(sl);

        // Insert between "there" and "sweetie"
        System.out.println("inserting \"Oh\"");
        if (slIter2.hasNext()) {
            slIter2.next();
            slIter2.add("Oh");
            System.out.println(sl);
        }

        System.out.println("removing \"there\"");
        slIter2.remove();
        System.out.println(sl);
        System.out.println("removing \"sweetie\"");
        slIter2.remove();
        System.out.println(sl);
        System.out.println("removing \"pie\"");
        slIter2.remove();
        System.out.println(sl);
        System.out.println("removing \"hello\"");
        SListIterator slIter3 = sl.iterator();
        slIter3.remove();
        System.out.println(sl);
    }
}
