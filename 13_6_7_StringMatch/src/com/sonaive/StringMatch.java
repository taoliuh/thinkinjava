package com.sonaive;

/**
 * Created by liutao on 12/31/15.
 */
public class StringMatch {
    public static void main(String[] args) {
        String str = "Thanks to Annie.";
        System.out.println(str.matches("^[A-Z].+\\.$"));
    }
}
