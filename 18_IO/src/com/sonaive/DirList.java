package com.sonaive;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by liutao on 2/18/16.
 */
public class DirList {
    public static void main(String[] args) {
        File file = new File("src/net/mindview/util/");
        System.out.println(file.getAbsolutePath());
        String[] list = file.list(new DirFilter("S.*\\.java"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String filename : list) {
            System.out.println(filename);
        }
    }

    static class DirFilter implements FilenameFilter {

        private Pattern pattern;

        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }
}
