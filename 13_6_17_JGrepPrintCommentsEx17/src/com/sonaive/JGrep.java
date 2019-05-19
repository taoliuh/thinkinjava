package com.sonaive;
// strings/JGrep.java
// A very simple version of the "grep" program.
// {Args: JGrep.java "\\b[Ssct]\\w+"}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static net.mindview.util.Print.*;

public class JGrep {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        /* How to remove
           this code.
         */
//        Pattern p = Pattern.compile("//\\s+.*", Pattern.MULTILINE);
        Pattern p = Pattern.compile("(\\b)[A-Z_]\\w*", Pattern.MULTILINE);
        // Iterate through the lines of the input file:
        Matcher m = p.matcher("");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        m.reset(sb.toString());
        while (m.find()) {
            print(m.group());
        }
    }

}
