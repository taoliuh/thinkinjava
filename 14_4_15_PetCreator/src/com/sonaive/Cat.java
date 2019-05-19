//: typeinfo/pets/Cat.java
package com.sonaive;

public class Cat extends Pet {
  public Cat(String name) { super(name); }
  public Cat() { super(); }
  public static class Factory implements com.sonaive.Factory<Cat> {

    @Override
    public Cat create() {
      return new Cat(Cat.class.getSimpleName());
    }
  }
} ///:~
