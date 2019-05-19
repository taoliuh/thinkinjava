package com.sonaive;//: generics/MultipleInterfaceVariants.java
// {CompileTimeError} (Won't compile)

interface Payable<T> {}

class Employee implements Payable {}
class Hourly extends Employee
  implements Payable {} ///:~
