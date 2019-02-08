package Model.Utils;

import java.util.Map;

public interface HeapInterface< K, V > {

    public void add(K key, V value);
    public boolean contains(K key);
    public void update(K key, V value);
    public void remove(K key);
    public V get(K key);
    public Iterable<K> getElements();
    public void setContent(Map<K, V> m);
    public Map<K,V> getHeap();
}