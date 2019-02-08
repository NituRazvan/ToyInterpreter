package Model.FileHandling;


import java.util.Collection;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public interface FileTableInterface<K, V> {

    void add(K key, V val);
    void delete(K key);
    V get(K key);
    boolean contains(K key);
    Iterable<K> getElements();
    Collection<V> getAllElems();
    void clear();
     void setMap(Map<K, V> m);
     Map<K, V> getMap();

    V getValue(K key);
   //List<BufferedReader> getValues();


}
