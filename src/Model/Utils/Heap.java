package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap {
    private HashMap<Integer, Integer> dictionary;
    private Integer key=0;

    public Heap(){dictionary =  new HashMap<>();}

    @Override
    public void put(Integer val) {
        this.key++;
        dictionary.put(this.key,val);
    }

    public void setContent(Map<Integer,Integer> map){
        this.dictionary=new HashMap<>(map);
    }

    public Map<Integer,Integer> getContent(){
        return this.dictionary;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key : dictionary.keySet())
            s.append(key.toString()).append("->").append(dictionary.get
                    (key).toString()).append("\n");
        return s.toString();
    }

    @Override
    public void remove(Integer key) {
        dictionary.remove(key);
    }

    public Integer getKey(){
        return this.key;
    }

    @Override
    public void update(Integer key,Integer val){
        this.dictionary.replace(key,val);
    }

    @Override
    public boolean contains(Integer key) {
        return dictionary.containsKey(key);
    }

    @Override
    public Integer get(Integer key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }
}
