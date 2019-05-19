//: typeinfo/pets/Rat.java
package com.sonaive;

public class Rat extends Rodent {
  public Rat(String name) { super(name); }
  public Rat() { super(); }
  public static class Factory implements com.sonaive.Factory<Rat> {

    @Override
    public Rat create() {
      return new Rat(Rat.class.getSimpleName());
    }
  }
} ///:~
