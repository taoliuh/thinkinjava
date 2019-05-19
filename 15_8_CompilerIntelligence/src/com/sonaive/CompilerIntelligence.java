package com.sonaive;//: generics/CompilerIntelligence.java
import java.util.*;

class Fruit {}

class Apple extends Fruit {}

class Jonathan extends Apple {}

class Orange extends Fruit {}

public class CompilerIntelligence {
  public static void main(String[] args) {
    List<? extends Fruit> flist =
      Arrays.asList(new Apple());
    Apple a = (Apple)flist.get(0); // No warning
    flist.contains(new Apple()); // Argument is 'Object'
    flist.indexOf(new Apple()); // Argument is 'Object'
    List<? extends Fruit> list = new ArrayList<>(flist);
//    list.add(new Apple());
//    list.add(new Orange());
  }
} ///:~
