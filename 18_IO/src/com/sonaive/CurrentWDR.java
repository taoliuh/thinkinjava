package com.sonaive;

import java.io.File;

public class CurrentWDR {

    public static void main(String[] args) throws Exception {

        System.out.println(System.getProperty("user.dir"));

        System.out.println(new File(".").getAbsolutePath());

        System.out.println(new File(".").getCanonicalPath());

        System.out.println(new File(".").getName());

        System.out.println(new File(".").getParent());

        System.out.println(new File(".").getPath());

        System.out.println(new File(".").getAbsoluteFile());

        System.out.println(new File(".").getCanonicalFile());

        System.out.println(new File(".").getParentFile());

    }

}