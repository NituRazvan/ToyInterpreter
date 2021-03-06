package Model.Utils;

import java.util.Map;
import java.util.Set;

public interface ProcTableInterface <K, V> {
        public void add(K key, V value);
        public boolean contains(K key);
        public void update(K key, V value);
        public void remove(K key);
        public V get(K key);
        public Iterable<K> getAll();
        public void setContent(Map<K,V> m);
        public Set<Map.Entry<K, V>> entrySet() ;
    }