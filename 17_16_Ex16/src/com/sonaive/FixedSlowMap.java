package com.sonaive;//: containers/SlowMap.java
// A Map implemented with ArrayLists.

import net.mindview.util.Countries;

import java.util.*;

public class FixedSlowMap<K,V> extends AbstractMap<K,V> {
  private List<K> keys = new ArrayList<K>();
  private List<V> values = new ArrayList<V>();
  private EntrySet entries = new EntrySet();
  public V put(K key, V value) {
    V oldValue = get(key); // The old value or null
    if(!keys.contains(key)) {
      keys.add(key);
      values.add(value);
    } else
      values.set(keys.indexOf(key), value);
    return oldValue;
  }
  public V get(Object key) { // key is type Object, not K
    if(!keys.contains(key))
      return null;
    return values.get(keys.indexOf(key));
  }
  public Set<Entry<K,V>> entrySet() {
    return entries;
  }

  private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

    @Override
    public Iterator<Entry<K, V>> iterator() {
      return new Iterator<Entry<K, V>>() {

        private int index = -1;

        @Override
        public boolean hasNext() {
          return index < keys.size() - 1;
        }

        @Override
        public Entry<K, V> next() {
          int i = ++index;
          return new MapEntry<>(keys.get(i), values.get(i));
        }

        @Override
        public void remove() {
          keys.remove(index);
          values.remove(index--);
        }
      };
    }

    @Override
    public int size() {
      return keys.size();
    }
  }
  public static void main(String[] args) {
    FixedSlowMap<String,String> m= new FixedSlowMap<String,String>();
    m.putAll(Countries.capitals(15));
    System.out.println(m);
    System.out.println(m.get("BULGARIA"));
    System.out.println(m.entrySet());
  }
} /* Output:
{CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti}
Sofia
[CAMEROON=Yaounde, CHAD=N'djamena, CONGO=Brazzaville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, CENTRAL AFRICAN REPUBLIC=Bangui, BOTSWANA=Gaberone, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, DJIBOUTI=Dijibouti]
*///:~
