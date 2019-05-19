package com.sonaive;

import java.util.HashMap;

/**
 * Created by liutao on 2/18/16.
 */
public class NullKeyValue {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put("Yes", "Sir");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put(null, "Value");
        System.out.println(map);
        System.out.println(map2);
    }
}
