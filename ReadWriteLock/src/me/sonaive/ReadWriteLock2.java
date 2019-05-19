package me.sonaive;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutao on 16/04/2019.
 */
public class ReadWriteLock2 {
    private Map<Thread, Integer> readingThreads = new HashMap<>();
    private int writers = 0;
    private int writeRequests = 0;
    private Thread writeThread;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantRead(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        int access = getReadAccessCount(callingThread);
        if (access == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, access - 1);
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        ++writeRequests;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWrite(callingThread)) {
            wait();
        }
        --writeRequests;
        ++writers;
        writeThread = callingThread;
    }

    public synchronized void unlockWrite() {
        --writers;
        if (writers == 0) {
            writeThread = null;
        }
        notifyAll();
    }

    private boolean canGrantRead(Thread thread) {
        if (isWriter(thread)) {
            return true;
        }
        if (writers > 0) {
            return false;
        }
        if (isReader(thread)) {
            return true;
        }
        if (writeRequests > 0) {
            return false;
        }
        return true;
    }

    private int getReadAccessCount(Thread thread) {
        Integer access = readingThreads.get(thread);
        if (access == null) {
            return 0;
        }
        return access;
    }

    private boolean isReader(Thread thread) {
        return readingThreads.get(thread) != null;
    }

    private boolean canGrantWrite(Thread thread) {
        if (hasReaders()) {
            return false;
        }
        if (writeThread == null) {
            return true;
        }
        if (!isWriter(thread)) {
            return false;
        }
        return true;
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean isWriter(Thread thread) {
        return thread == writeThread;
    }
}
