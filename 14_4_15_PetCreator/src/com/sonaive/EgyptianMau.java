//: typeinfo/pets/EgyptianMau.java
package com.sonaive;

public class EgyptianMau extends Cat {
  public EgyptianMau(String name) { super(name); }
  public EgyptianMau() { super(); }
  public static class Factory implements com.sonaive.Factory<EgyptianMau> {

    @Override
    public EgyptianMau create() {
      return new EgyptianMau(EgyptianMau.class.getSimpleName());
    }
  }
} ///:~
