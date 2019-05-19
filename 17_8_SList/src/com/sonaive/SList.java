package com.sonaive;

/**
 * Created by liutao on 1/30/16.
 */
public class SList<E> {
    private Link<E> headLink = new Link<E>(null);
    SListIterator<E> iterator() { return new SListIterator<E>(headLink); }
    public String toString() {
        if(headLink.next == null) return "SList: []";
        SListIterator<E> it = this.iterator();
        StringBuilder s = new StringBuilder("SList: [");
        while(it.hasNext()) {
            s.append(it.next() + (it.hasNext() ? ", " : ""));
        }
        return s + "]";
    }
}
