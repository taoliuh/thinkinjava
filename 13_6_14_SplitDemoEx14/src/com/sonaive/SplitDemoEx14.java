package com.sonaive;

import java.util.Arrays;

/**
 * Created by liutao on 1/1/16.
 */
public class SplitDemoEx14 {
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(input.split("!!")));
        System.out.println(Arrays.toString(input.split("!!", 3)));
    }
}
