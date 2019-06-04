package Model.Utils;
import Model.Utils.Pair;

import java.io.BufferedReader;

public interface IFileTable {
    void put(Integer key,String filename, BufferedReader br);
    void remove(Integer key);
    boolean contains(Integer key);
    Pair<String,BufferedReader> get(Integer key);
    String toString();
    boolean isEmpty();
}
