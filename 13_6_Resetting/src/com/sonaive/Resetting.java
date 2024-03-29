package com.sonaive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liutao on 1/1/16.
 */
public class Resetting {
    public static void main(String[] args) throws Exception {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the rug with bags, fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
}
