//: typeinfo/pets/Cymric.java
package com.sonaive;

public class Cymric extends Manx {
  public Cymric(String name) { super(name); }
  public Cymric() { super(); }
  public static class Factory implements com.sonaive.Factory<Cymric> {

    @Override
    public Cymric create() {
      return new Cymric(Cymric.class.getSimpleName());
    }
  }
} ///:~
