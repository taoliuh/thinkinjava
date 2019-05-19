//: typeinfo/pets/Mouse.java
package com.sonaive;

public class Mouse extends Rodent {
  public Mouse(String name) { super(name); }
  public Mouse() { super(); }
  public static class Factory implements com.sonaive.Factory<Mouse> {

    @Override
    public Mouse create() {
      return new Mouse(Mouse.class.getSimpleName());
    }
  }
} ///:~
