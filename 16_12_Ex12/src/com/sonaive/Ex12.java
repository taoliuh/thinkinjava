package com.sonaive;

import net.mindview.util.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by liutao on 1/19/16.
 */
public class Ex12 {
    public static void test(Class<CountingGenerator.Double> generatorClass) {
        System.out.print(generatorClass.getSimpleName() + ": ");
        try {
            Generator gen = generatorClass.newInstance();
            Random random = new Random(47);
            int size = random.nextInt(15);
            double[] doubles = new double[size];
            for (int i = 0; i < size; i++) {
                doubles[i] = (double) gen.next();
            }
            System.out.println(Arrays.toString(doubles));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test(CountingGenerator.Double.class);
    }
}
