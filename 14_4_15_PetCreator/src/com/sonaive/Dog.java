//: typeinfo/pets/Dog.java
package com.sonaive;

public class Dog extends Pet {
  public Dog(String name) { super(name); }
  public Dog() { super(); }
  public static class Factory implements com.sonaive.Factory<Dog> {

    @Override
    public Dog create() {
      return new Dog(Dog.class.getSimpleName());
    }
  }
} ///:~
