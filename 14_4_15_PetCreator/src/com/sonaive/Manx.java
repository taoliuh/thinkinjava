//: typeinfo/pets/Manx.java
package com.sonaive;

public class Manx extends Cat {
  public Manx(String name) { super(name); }
  public Manx() { super(); }
  public static class Factory implements com.sonaive.Factory<Manx> {

    @Override
    public Manx create() {
      return new Manx(Manx.class.getSimpleName());
    }
  }
} ///:~
