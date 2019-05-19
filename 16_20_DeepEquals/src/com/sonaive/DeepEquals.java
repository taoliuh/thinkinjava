package com.sonaive;

import java.util.Arrays;

/**
 * Created by liutao on 1/20/16.
 */

class Equals {
    private int i;
    public Equals(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        Equals other = (Equals) obj;
        return this.i == other.i;
    }
}

public class DeepEquals {
    public static void main(String[] args) {
        Equals[][] e1 = new Equals[3][];
        Equals[][] e2 = new Equals[3][];
        for (int i = 0; i < e1.length; i++) {
            e1[i] = new Equals[5];
            for (int j = 0; j < e1[i].length; j++) {
                e1[i][j] = new Equals(1);
            }
        }

        for (int i = 0; i < e2.length; i++) {
            e2[i] = new Equals[5];
            for (int j = 0; j < e2[i].length; j++) {
                e2[i][j] = new Equals(1);
            }
        }

        System.out.println(Arrays.equals(e1, e2));
        System.out.println(Arrays.deepEquals(e1, e2));

        Equals[][] e3 = {{new Equals(0), new Equals(0)}, null};
        Equals[][] e4 = {{new Equals(0), new Equals(0)}, null};
        System.out.println(Arrays.deepEquals(e3, e4));
    }
}
