package me.sonaive;

/**
 * Created by liutao on 16/04/2019.
 */
public class ReadWriteLock {
    private int readCount = 0;
    private int writeCount = 0;
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writeCount > 0 || writeRequests > 0) {
            wait();
        }
        ++readCount;
    }

    public synchronized void unlockRead() {
        --readCount;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        ++writeRequests;
        while (readCount > 0 || writeCount > 0) {
            wait();
        }
        --writeRequests;
        ++writeCount;
    }

    public synchronized void unlockWrite() {
        --writeCount;
        notifyAll();
    }

    public synchronized int getReadCount() {
        return readCount;
    }
}
