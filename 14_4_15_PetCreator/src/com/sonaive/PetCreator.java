//: typeinfo/pets/PetCreator.java
// Creates random sequences of Pets.
package com.sonaive;

import java.util.*;

public class PetCreator {
    private static Random rand = new Random(47);

    static List<Factory<? extends Pet>> petFactories = new ArrayList<>();

    static {
        Collections.addAll(petFactories, new Cat.Factory(),
                new Cymric.Factory(),
                new Dog.Factory(),
                new EgyptianMau.Factory(),
                new Hamster.Factory(),
                new Manx.Factory(),
                new Mouse.Factory(),
                new Mutt.Factory(),
                new Pug.Factory(),
                new Rat.Factory(),
                new Rodent.Factory()
                );
    }

    public Pet randomPet() { // Create one random Pet
        int n = rand.nextInt(petFactories.size());
        return petFactories.get(n).create();
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++)
            result[i] = randomPet();
        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<>();
        Collections.addAll(result, createArray(size));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Pets.arrayList(10));
    }
} ///:~
