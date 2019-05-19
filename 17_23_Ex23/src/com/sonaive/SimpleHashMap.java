package com.sonaive;

import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Created by liutao on 2/17/16.
 */
public class SimpleHashMap<K, V> implements Map<K, V> {

    private static final int SIZE = 1 << 6;

    private LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (isEmpty()) {
            return false;
        }
        int index = key == null ? 0 : Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] != null) {
            for (MapEntry<K, V> entry : buckets[index]) {
                if (entry.getKey() == key || (key != null && entry.getKey().equals(key))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (value == null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getValue() == null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] != null) {
            for (MapEntry<K, V> bucket : buckets[index]) {
                if (bucket.getKey().equals(key)) {
                    return bucket.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        boolean found = false;
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        ListIterator<MapEntry<K, V>> iterator = buckets[index].listIterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> next = iterator.next();
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
    public V remove(Object key) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] != null) {
            for (MapEntry<K, V> entry : buckets[index]) {
                if (entry.getKey().equals(key)) {
                    oldValue = entry.getValue();
                    int i = buckets[index].indexOf(entry);
                    buckets[index].remove(i);
                    break;
                }
            }
        }
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntry<K, V> entry : bucket) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Set<V> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntry<K, V> entry : bucket) {
                set.add(entry.getValue());
            }
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleHashMap)) {
            return false;
        }
        SimpleHashMap<K, V> map = (SimpleHashMap<K, V>) obj;
        return entrySet().equals(map.entrySet());
    }

    @Override
    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return this.entrySet().toString();
    }

    public static void main(String[] args) {
        SimpleHashMap<String,String> map =
                new SimpleHashMap<>();
        map.putAll(Countries.capitals(3));
        print("map = " + map);
        print("map.entrySet(): " + map.entrySet());
        print("map.keySet(): " + map.keySet());
        print("map.values() = " + map.values());
        print("map.isEmpty(): " + map.isEmpty());
        print("map.containsKey(\"ALGERIA\"): " + map.containsKey("ALGERIA"));
        print("map.containsValue(\"Algiers\"): " + map.containsValue("Algiers"));
        print("map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        print("map.remove(\"ALGERIA\"): " + map.remove("ALGERIA"));
        print("After map.remove(\"ALGERIA\"), map.containsKey(\"ALGERIA\"): " +
                map.containsKey("ALGERIA"));
        print(" and map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        print(" and map: = " + map);
        map.clear();
        print("After map.clear(), map = " + map);
        print(" and map.isEmpty(): " + map.isEmpty());
        map.putAll(Countries.capitals(3));
        print("After map.putAll(Countries.capitals(3)), map = " + map);
        SimpleHashMap<String,String> map2 =
                new SimpleHashMap<>();
        map2.putAll(Countries.capitals(4));
        print("After map2.putAll(Countries.capitals(4)), map2 = " + map2);
        print(" and map.equals(map2): " + map.equals(map2));
        map2.remove("BOTSWANA");
        print("After map2.remove(\"BOTSWANT\"), map.equals(map2): " + map.equals(map2));
        map.entrySet().clear();
        print("After map.entrySet().clear, map = " + map);
        map.putAll(Countries.capitals(3));
        print("After map.putAll(Countries.capitals(3)), map = " + map);
        map.keySet().clear();
        print("After map.keySet().clear(), map = " + map);
    }
}
