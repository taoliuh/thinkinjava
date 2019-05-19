//: typeinfo/pets/Person.java
package com.sonaive;

public class Person extends Individual {
  public Person(String name) { super(name); }
  public static class Factory implements com.sonaive.Factory<Person> {

      @Override
      public Person create() {
          return new Person(Person.class.getSimpleName());
      }
  }
} ///:~
