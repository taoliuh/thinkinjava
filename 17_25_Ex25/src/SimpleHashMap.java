import java.util.*;

/**
 * Created by liutao on 3/20/16.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    MapEntry<K, V>[] table = new MapEntry[997];


    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    private final class HashIterator<E> implements Iterator<E> {

        private MapEntry<K, V> current;
        private MapEntry<K, V> next;
        private int index;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }


}
