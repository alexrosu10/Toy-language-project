package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K,
 V       > {

    private HashMap<K, V> dictionary;

    public MyDictionary(){
        dictionary =  new HashMap<K,V>();
    }

    public MyDictionary(Map<K,V> m){
        this.dictionary=new HashMap<>(m);
    }

    public MyDictionary(MyDictionary<K,V> md){
        this(md.getContent());
    }

    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public void remove(K key) {
        dictionary.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public V get(K key) {
        return dictionary.get(key);
    }

    public Map<K,V> getContent(){
        return this.dictionary;
    }

    public void setContent(Map<K,V> m){
        this.dictionary=new HashMap<>(m);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(K key : dictionary.keySet())
            s.append(key.toString()).append("->").append(dictionary.get
                    (key).toString()).append("\n");
        return s.toString();
    }

    public void update(K key, V value){
        dictionary.replace(key,value);
    }
}
