package Model.Utils;

import Model.Statement.IStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcTable implements IProcTable {
    private HashMap<String,Pair<ArrayList<String>,IStatement>> dict;

    public ProcTable(){
        this.dict=new HashMap<>();
    }

    public ProcTable(Map<String,Pair<ArrayList<String>,IStatement>> m){
        this.dict=new HashMap<>(m);
    }

    public ProcTable(ProcTable pc){this(pc.getContent());}

    @Override
    public void put(String key, Pair<ArrayList<String>, IStatement> value) throws Exception {
        if(dict.containsKey(key)){
            throw new Exception("Procedure already in table");
        }
        else {
            dict.put(key, value);
        }
    }

    @Override
    public void remove(String key) {
        dict.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return dict.containsKey(key);
    }

    @Override
    public Pair<ArrayList<String>, IStatement> get(String key) {
        return dict.get(key);
    }

    public Map<String,Pair<ArrayList<String>,IStatement>> getContent(){
        return dict;
    }

    public void setContent(Map<String,Pair<ArrayList<String>,IStatement>> m){
        this.dict=new HashMap<>(m);
    }

    public String toString(){
        String s="";
        for(String key:dict.keySet()){
            s+=key;
            s+="->(";
            for(String str:dict.get(key).x){
                s+=str;
                s+=",";
            }
            s+=") ";
            s+=dict.get(key).y.toString();
            s+="\n";
        }
        return s;
    }
}
