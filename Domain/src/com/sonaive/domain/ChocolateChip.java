package com.sonaive.domain;

/**
 * Created by liutao on 12/23/15.
 */
public class ChocolateChip {

    public ChocolateChip() {
        System.out.println("Chocolate chip constructor");
    }

    public void chomp() {
        Cookie cookie = new Cookie();
        cookie.bite();
    }
}
