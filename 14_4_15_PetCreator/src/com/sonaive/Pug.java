//: typeinfo/pets/Pug.java
package com.sonaive;

public class Pug extends Dog {
  public Pug(String name) { super(name); }
  public Pug() { super(); }
  public static class Factory implements com.sonaive.Factory<Pug> {

    @Override
    public Pug create() {
      return new Pug(Pug.class.getSimpleName());
    }
  }
} ///:~
