package me.sonaive;

/**
 * Created by liutao on 27/02/2019.
 */
public abstract class AbstractConsumer implements Consumer, Runnable {

    protected final int id;

    public AbstractConsumer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                consume();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer-" + id + " interrupted!");
        }
    }
}
