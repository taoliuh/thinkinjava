package com.sonaive;

import net.mindview.util.TextFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import static net.mindview.util.Print.*;

/**
 * Created by liutao on 1/26/16.
 */
public class Ex4 {
    static Collection<String> readFromText(String filename) {
        return new ArrayList<>(new TextFile(filename, "\\W+"));
    }

    static Collection<String> readFromText2(String filename) {
        return new TreeSet<>(new TextFile(filename, "\\W+"));
    }

    public static void main(String[] args) {
        print("ArrayList: " + readFromText("17_4_Ex4/src/com/sonaive/Ex4.java"));
        print("TreeSet: " + readFromText2("17_4_Ex4/src/com/sonaive/Ex4.java"));
    }
}
