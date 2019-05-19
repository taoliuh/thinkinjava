package com.sonaive;
//: strings/JGrep.java
// A very simple version of the "grep" program.
// {Args: JGrep.java "\\b[Ssct]\\w+"}

import java.io.File;
import java.util.ArrayList;
import java.util.regex.*;

import net.mindview.util.*;

public class JGrep {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile("\\b[Ssct]\\w+", Pattern.MULTILINE);
        // Iterate through the lines of the input file:

        Matcher m = p.matcher("");
        String name = args[0];
        File file = new File(name);
        if (file.isFile()) {
            print(m, new TextFile(name));
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                print(m, new TextFile(f.getAbsolutePath()));
            }
        }

    }

    private static void print(Matcher matcher, ArrayList<String> lines) {
        int index = 0;
        for (String line : lines) {
            matcher.reset(line);
            while (matcher.find())
                System.out.println(index++ + ": " +
                        matcher.group() + ": " + matcher.start());
        }
    }
}
/* Output: (Sample)
0: strings: 4
1: simple: 10
2: the: 28
3: Ssct: 26
4: class: 7
5: static: 9
6: String: 26
7: throws: 41
8: System: 6
9: System: 6
10: compile: 24
11: through: 15
12: the: 23
13: the: 36
14: String: 8
15: System: 8
16: start: 31
*///:~
