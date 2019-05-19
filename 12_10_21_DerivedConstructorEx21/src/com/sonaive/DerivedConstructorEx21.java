package com.sonaive;

/**
 * Created by liutao on 12/30/15.
 */

class BaseException extends Exception {}

class A {
    public A() throws BaseException {
        throw new BaseException();
    }
}

class B extends A {

    public B() throws Exception {
//        super();
//        try {
//        } catch (BaseException e) {
//
//        }


    }
}

public class DerivedConstructorEx21 {

    public static void main(String[] args) {
        try {
            B b = new B();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
