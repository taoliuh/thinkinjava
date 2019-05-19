package com.sonaive;

import java.util.concurrent.TimeUnit;

/**
 * Created by liutao on 1/8/16.
 */
public class NonTask {
    public static void sleep()  {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted!");
        }
    }
}
