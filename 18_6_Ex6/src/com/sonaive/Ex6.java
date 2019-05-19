package com.sonaive;

import net.mindview.util.ProcessFiles;

import java.io.File;

/**
 * Created by liutao on 2/22/16.
 */
public class Ex6 {
    public static void main(String[] args) {
        final long lastModify = 1456120142000l;
        new ProcessFiles(new ProcessFiles.Strategy() {
            @Override
            public void process(File file) {
                if (file.lastModified() > lastModify) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }, "java").start(args);
    }
}
