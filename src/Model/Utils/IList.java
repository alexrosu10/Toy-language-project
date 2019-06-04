package Model.Utils;

public interface IList<T> {
    void add(T elem);
    void remove(T elem);
    String toString();
    int size();
}
