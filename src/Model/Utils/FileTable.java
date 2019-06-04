package Model.Utils;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Model.Utils.Pair;

public class FileTable implements IFileTable{
    private HashMap<Integer, Pair<String, BufferedReader>> dictionary;

    public FileTable(){
        dictionary =  new HashMap<>();
    }

    @Override
    public void put(Integer key,String filename,BufferedReader br) {
        Pair<String,BufferedReader> p=new Pair<>(filename,br);
        dictionary.put(key, p);
    }

    @Override
    public void remove(Integer key) {
        dictionary.remove(key);
    }

    @Override
    public boolean contains(Integer key) {
        return dictionary.containsKey(key);
    }

    @Override
    public Pair<String, BufferedReader> get(Integer key) {
        return dictionary.get(key);
    }

    public Map<Integer,Pair<String, BufferedReader>> getContent(){
        return this.dictionary;
    }

    public void setContent(Map<Integer,Pair<String, BufferedReader>> m){
        this.dictionary=new HashMap<>(m);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Integer key : dictionary.keySet())
            s.append(key.toString()).append("->").append(dictionary.get
                    (key).x).append("\n");
        return s.toString();
    }

    public Set<Integer> keySet(){
        return this.dictionary.keySet();
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }
}
