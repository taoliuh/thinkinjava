//: typeinfo/pets/Hamster.java
package com.sonaive;

public class Hamster extends Rodent {
  public Hamster(String name) { super(name); }
  public Hamster() { super(); }
  public static class Factory implements com.sonaive.Factory<Hamster> {

    @Override
    public Hamster create() {
      return new Hamster(Hamster.class.getSimpleName());
    }
  }
} ///:~
