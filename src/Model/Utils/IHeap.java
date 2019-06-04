package Model.Utils;

public interface IHeap{
    void put(Integer val);
    void remove(Integer key);
    void update(Integer key,Integer val);
    boolean contains(Integer key);
    Integer get(Integer key);
    String toString();
    boolean isEmpty();
}
