package Model.Utils;

import java.util.HashMap;
import java.util.Map;
//valori si adrese symTable care pointeaza spre heap
public class Heap<K, V> implements  HeapInterface<K, V> {

    Map<K, V> dict = new HashMap<>();

    @Override
    public void add(K key, V value) {
        dict.put(key, value);
    }

    @Override
    public boolean contains(K key) {
        return dict.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        dict.put(key, value);
    }

    @Override
    public void remove(K key) {
        dict.remove(key);
    }

    @Override
    public V get(K key) {
        return dict.get(key);
    }

    @Override
    public Iterable<K> getElements() {
        return dict.keySet();
    }

    @Override
    public void setContent(Map<K, V> m) {
        dict = m;
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();

        for(Map.Entry<K, V> e : dict.entrySet()){
            str.append("<"+e.getKey() + ", " + e.getValue() + ">; ");
        }
        return str.toString();
    }
    @Override
    public Map<K,V> getHeap(){
        return this.dict;
    }

}
