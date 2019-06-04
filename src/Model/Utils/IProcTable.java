package Model.Utils;

import Model.Statement.IStatement;

import java.util.ArrayList;

public interface IProcTable {
    void put(String key, Pair<ArrayList<String>, IStatement> value) throws Exception;
    void remove(String key);
    boolean contains(String key);
    Pair<ArrayList<String>, IStatement> get(String key);
    String toString();
}
