package com.sonaive;

/**
 * Created by liutao on 1/19/16.
 */
public class CaseInsensitiveComparatorTest {
    public static void main(String[] args) {
        char ch1 = (char) 73; //LATIN CAPITAL LETTER I
        char ch2 = (char) 304; //LATIN CAPITAL LETTER I WITH DOT ABOVE
        System.out.println(ch1==ch2);
        System.out.println(Character.toUpperCase(ch1)==Character.toUpperCase(ch2));
        System.out.println(Character.toLowerCase(ch1)==Character.toLowerCase(ch2));
    }
}
