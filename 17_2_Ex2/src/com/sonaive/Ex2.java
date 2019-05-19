package com.sonaive;

import net.mindview.util.Countries;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by liutao on 1/26/16.
 */
public class Ex2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Set<String> set = map.keySet();
        Pattern pattern = Pattern.compile("A.*");
        int size = Countries.DATA.length;
        for (int i = 0; i < size; i++) {
            if (pattern.matcher(Countries.DATA[i][0]).matches()) {
                map.put(Countries.DATA[i][0], Countries.DATA[i][1]);
            }
        }
        System.out.println(map);
        System.out.println(set);
    }
}
