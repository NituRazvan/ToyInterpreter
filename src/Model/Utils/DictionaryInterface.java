package Model.Utils;

import java.util.List;
import java.util.Map;

public interface DictionaryInterface<K, V> {
    void setValue(K key, V value);
    void delete(K key);
    V getValue(K key);
    List<V> getAllValues();
    boolean exists(K key);
    Iterable<K> getElements();
    DictionaryInterface<K, V> copy();
    void setMap(Map<K, V> d);
    Map<K, V> getMap();
}
