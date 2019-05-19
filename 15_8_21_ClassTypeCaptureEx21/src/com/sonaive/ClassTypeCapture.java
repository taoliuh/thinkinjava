package com.sonaive;

import java.util.HashMap;
import java.util.Map;
import static net.mindview.util.Print.*;

/**
 * Created by liutao on 1/4/16.
 */

class Building {}

class House extends Building {}

public class ClassTypeCapture<T> {
    Class<?> kind;
    Map<String,Class<?>> map;
    public ClassTypeCapture(Class<?> kind) {
        this.kind = kind;
    }
    public ClassTypeCapture(Class<?> kind, Map<String,Class<?>> map) {
        this.kind = kind;
        this.map = map;
    }
    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }
    public void addType(String typename, Class<?> kind) {
        map.put(typename, kind);
    }
    public Object createNew(String typename)
            throws IllegalAccessException, InstantiationException {
        if(map.containsKey(typename))
            return map.get(typename).newInstance();
        System.out.print(typename + " class not available");
        return null;
    }
    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        print(ctt1.f(new Building()));
        print(ctt1.f(new House()));
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        print(ctt2.f(new Building()));
        print(ctt2.f(new House()));
        ClassTypeCapture<Building> ct =
                new ClassTypeCapture<>(Building.class, new HashMap<String, Class<?>>());
        ct.addType("House", House.class);
        ct.addType("Building", Building.class);
        print("ct.map = " + ct.map);
        try {
            Building b = (Building)ct.createNew("Building");
            House h = (House)ct.createNew("House");
            print("b.getClass().getName(): ");
            print(b.getClass().getName());
            print("h.getClass().getName(): ");
            print(h.getClass().getName());
            print("House h is instance House: ");
            print(h instanceof House);
            print("House h is instance of Building: ");
            print(h instanceof Building);
            print("Building b is instance of House: ");
            print(b instanceof House);
            ct.createNew("String");  // String class not available
        } catch(IllegalAccessException e) {
            print("IllegalAccessException in main");
        } catch(InstantiationException e) {
            print("InstantiationException in main");
        }
    }
}
