package me.sonaive;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by liutao on 16/04/2019.
 */
public class ReadWriterList<T> {
    private ArrayList<T> lockedList;

    private ReadWriteLock lock = new ReadWriteLock();

    public ReadWriterList(int size, T initialValue) {
        lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) throws InterruptedException {
        lock.lockWrite();
        try {
            return lockedList.set(index, element);
        } finally {
            lock.unlockWrite();
        }
    }

    public T get(int index) throws InterruptedException {
        lock.lockRead();
        try {
            if (lock.getReadCount() > 1) {
                System.out.println("read lock count " + lock.getReadCount());
            }
            return lockedList.get(index);
        } finally {
            lock.unlockRead();
        }
    }

    public static void main(String[] args) {
        new LockTest(30, 2);
    }
}
