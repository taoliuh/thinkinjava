package com.sonaive;

import java.util.Map;

/**
 * Created by liutao on 2/17/16.
 */
public class MapEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public MapEntry(K k, V v) {
        key = k;
        value = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V result = this.value;
        this.value = value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MapEntry)) {
            return false;
        }
        MapEntry me = (MapEntry) obj;
        return key == null ? me.key == null : key.equals(me.key)
                && value == null ? me.value == null : value.equals(me.value);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (key == null ? 0 : key.hashCode());
        result = 37 * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}
