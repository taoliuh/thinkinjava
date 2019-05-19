package com.sonaive;

/**
 * Created by liutao on 1/5/16.
 */

class Generic1<T> {
    T t;
    void a(T t) {
        this.t = t;
    }
}

class Generic2<T> {
    T t;
    T b() {
        return t;
    }
}

public class Ex28 {
    <T> void c(Generic1<? super T> gen, T t) {
        gen.a(t);
    }

    <T> void d(Generic2<? extends T> gen) {
        gen.b();
    }

    public static void main(String[] args) {
        Ex28 ex = new Ex28();
        ex.c(new Generic1<Integer>(), 1);
        ex.d(new Generic2<Float>());
    }
}
