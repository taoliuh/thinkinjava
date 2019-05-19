//: typeinfo/pets/Mutt.java
package com.sonaive;

public class Mutt extends Dog {
  public Mutt(String name) { super(name); }
  public Mutt() { super(); }
  public static class Factory implements com.sonaive.Factory<Mutt> {

    @Override
    public Mutt create() {
      return new Mutt(Mutt.class.getSimpleName());
    }
  }
} ///:~
