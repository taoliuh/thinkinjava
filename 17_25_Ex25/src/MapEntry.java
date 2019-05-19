import java.util.Map;
import java.util.Objects;

/**
 * Created by liutao on 3/20/16.
 */
public class MapEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;
    private MapEntry<K, V> next;

    public MapEntry(K key, V value, MapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
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
        V oldValue = getValue();
        this.value = value;
        return oldValue;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MapEntry)) {
            return false;
        }
        Map.Entry<K, V> me = (Map.Entry<K, V>) obj;
        return key == null ? me.getKey() == null : key.equals(me.getKey())
                && value == null ? me.getValue() == null : value.equals(me.getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
