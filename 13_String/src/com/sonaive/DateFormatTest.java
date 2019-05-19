package com.sonaive;

/**
 * Created by liutao on 2/21/16.
 */
public class DateFormatTest {
    public static void main(String[] args) {
        String s = "2006-04-15 02:31:04";
        String regex = "\\D";
        String result = s.replaceAll(regex, "");
        System.out.println(result);
    }
}
