package Model.FileHandling;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class FileTable<K, V> implements FileTableInterface<K, V>{

    private Map<K, V> dic = new HashMap<>();


    @Override
    public void setMap(Map<K, V> m) {
        this.dic = m;
    }

    @Override
    public Map<K, V> getMap() {
        return this.dic;
    }

    @Override
    public void add(K key, V val) {
        dic.put(key, val);
    }

    @Override
    public void delete(K key) {
        dic.remove(key);
    }

    @Override
    public V get(K key) {
        return dic.get(key);
    }

    @Override
    public V getValue(K key) {
        return dic.get(key);
    }

    @Override
    public boolean contains(K key) {
        return dic.containsKey(key);
    }


    @Override
    public Collection<K> getElements() {
        return dic.keySet();
    }

    @Override
    public Collection<V> getAllElems() {
        return dic.values();
    }

    @Override
    public String toString(){
        StringBuilder bld = new StringBuilder();
        for(Map.Entry<K, V> e : dic.entrySet()){
            bld.append("<").append(e.getKey()).append(",").append(e.getValue()).append(">;");
        }
        return bld.toString();
    }
    @Override
    public void clear() {
        dic.clear();
    }
}
