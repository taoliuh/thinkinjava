package me.sonaive;

/**
 * Created by liutao on 27/02/2019.
 */
public abstract class AbstractProducer implements Producer, Runnable {

    protected final int id;

    public AbstractProducer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                produce();
            }
        } catch (InterruptedException e) {
            System.out.println("Producer-" + id + " interrupted!");
        }
    }
}
