package com.sonaive;

/**
 * Created by liutao on 2/2/16.
 */
public class SListIterator<E> {

    Link<E> current;

    SListIterator(Link<E> link) {
        current = link;
    }

    public boolean hasNext() {
        return current.next != null;
    }

    public Link<E> next() {
        current = current.next;
        return current;
    }

    public void add(E e) {
        current.next = new Link<>(e, current.next);
        current = current.next;
    }

    public void remove() {
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
}
