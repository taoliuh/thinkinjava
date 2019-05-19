//: typeinfo/pets/Rodent.java
package com.sonaive;

public class Rodent extends Pet {
  public Rodent(String name) { super(name); }
  public Rodent() { super(); }
  public static class Factory implements com.sonaive.Factory<Rodent> {

    @Override
    public Rodent create() {
      return new Rodent(Rodent.class.getSimpleName());
    }
  }
} ///:~
