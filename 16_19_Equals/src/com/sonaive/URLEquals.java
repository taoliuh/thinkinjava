package com.sonaive;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liutao on 2/19/16.
 */
public class URLEquals {
    private static final String[] URLNAMES = {
            "http://www.sina.com.cn",
            "http://www.nwu.edu.cn",
            "http://javapuzzlers.com",
            "http://www.google.com",
            "http://Javapuzzlers.com",
            "http://apache2-snort.skybar.dreamhost.com"
    };

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        Set<URI> fav = new HashSet<>();
        for (String url : URLNAMES) {
            fav.add(new URI(url));
        }
        System.out.println(fav.size());
    }
}
