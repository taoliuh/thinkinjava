package me.sonaive;

/**
 * Created by liutao on 27/02/2019.
 */
public interface Model {
    Runnable newProducerRunnable(int id);
    Runnable newConsumerRunnable(int id);
}
