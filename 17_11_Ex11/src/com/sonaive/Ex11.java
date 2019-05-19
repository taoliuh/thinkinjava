package com.sonaive;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by liutao on 2/3/16.
 */

class Comp implements Comparable<Comp> {

    Integer i;
    Random rand = new Random();

    public Comp() {
        i = rand.nextInt(100);
    }

    @Override
    public int compareTo(Comp o) {
        return this.i.compareTo(o.i);
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}


public class Ex11 {
    public static void main(String[] args) {
        PriorityQueue<Comp> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer(new Comp());
        }
        while (!queue.isEmpty()) {
            Comp poll = queue.poll();
            System.out.print(poll + " ");
        }
    }
}
