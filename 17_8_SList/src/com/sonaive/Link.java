package com.sonaive;

/**
 * Created by liutao on 2/2/16.
 */
public class Link<E> {
    E e;
    Link<E> next;

    public Link(E e, Link<E> next) {
        this.e = e;
        this.next = next;
    }

    public Link(E e) {
        this.e = e;
        this.next = null;
    }

    @Override
    public String toString() {
        if (e == null) {
            return "null";
        }
        return e.toString();
    }
}
