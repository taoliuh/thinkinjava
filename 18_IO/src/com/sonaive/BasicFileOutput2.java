package com.sonaive;//: io/BasicFileOutput.java
import java.io.*;

public class BasicFileOutput2 {
  static String file = "18_IO/src/com/sonaive/BasicFileOutput.out";
  public static void main(String[] args)
  throws IOException {
    BufferedReader in = new BufferedReader(
      new StringReader(
        BufferedInputFile.read("18_IO/src/com/sonaive/BasicFileOutput.java")));
    BufferedWriter out = new BufferedWriter(new FileWriter(file));
    int lineCount = 1;
    String s;
    while((s = in.readLine()) != null ) {
      out.write(lineCount++ + ": " + s + "\n");
    }
    out.close();
    // Show the stored file:
    System.out.println(BufferedInputFile.read(file));
  }
} /* (Execute to see output) *///:~
