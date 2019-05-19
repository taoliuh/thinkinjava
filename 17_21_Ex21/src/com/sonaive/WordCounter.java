package com.sonaive;

import net.mindview.util.TextFile;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by liutao on 2/3/16.
 */
public class WordCounter {

    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>(new TextFile("src/net/mindview/util/TextFile.java", "\\W+"));
        int size = set.size();
        SimpleHashMap<String, Integer> counter = new SimpleHashMap<>();
        ArrayList<String> fileList = new TextFile("src/net/mindview/util/TextFile.java", "\\W+");
        // Count appearances of each unique word and add to array:
        for(String s : set) {
            int count = 0;
            for(String t : fileList) {
                if(t.equals(s)) count++;
            }
            counter.put(s, count);
        }
        System.out.println(counter);
    }
}
