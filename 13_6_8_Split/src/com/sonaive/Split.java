package com.sonaive;

import java.util.Arrays;

/**
 * Created by liutao on 12/31/15.
 */
public class Split {
    public static void main(String[] args) {
        String knights = "Then, when you have found the shrubbery, you must" +
                "cut down the mightiest tree in the forest..." +
                "with... a herring!";
        System.out.println(Arrays.toString(knights.split("the|you")));
    }
}
