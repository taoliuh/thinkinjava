package com.sonaive;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by liutao on 2/17/16.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    private static final int SIZE = 1 << 4;

    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        } else {
            System.out.printf("bucket %d exists!\n", index);
        }
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        boolean found = false;
        V oldValue = null;
        ListIterator<MapEntry<K, V>> iterator = buckets[index].listIterator();
        int counter = 0;
        while (iterator.hasNext()) {
            MapEntry<K, V> next = iterator.next();
            ++counter;
            if (counter > 1) {
                System.out.printf("Iteration times %d\n", counter);
            }
            if (next.getKey().equals(key)) {
                oldValue = next.getValue();
                iterator.set(entry);
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(entry);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        for (MapEntry<K, V> next : bucket) {
            if (next.getKey().equals(key)) {
                return next.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashSet<Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntry<K, V> entry : bucket) {
                set.add(entry);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.putAll(Countries.capitals());
        System.out.println(map);
    }
}
