package com.sonaive;

/**
 * Created by liutao on 12/31/15.
 */
public class Replace {
    public static void main(String[] args) {
        String knights = "Then, when you have found the shrubbery, you must" +
                "cut down the mightiest tree in the forest..." +
                "with... a herring!";
        System.out.println(knights.replaceAll("[aeiouAEIOU]", "_"));
    }
}
