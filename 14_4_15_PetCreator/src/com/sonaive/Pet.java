//: typeinfo/pets/Pet.java
package com.sonaive;

public class Pet extends Individual {
  public Pet(String name) { super(name); }
  public Pet() { super(); }
  public static class Factory implements com.sonaive.Factory<Pet> {

    @Override
    public Pet create() {
      return new Pet(Pet.class.getSimpleName());
    }
  }
} ///:~
