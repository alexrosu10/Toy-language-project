package Model.Utils;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {
    ArrayList<T> list;
    public MyList(){
        list = new ArrayList<T>();
    }
    @Override
    public void add(T elem) {
        list.add(elem);
    }

    public int size(){
        return list.size();
    }

    @Override
    public void remove(T elem) {
        list.remove(elem);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(T elem : list)
            s.append(elem.toString()).append("\n");
        return s.toString();
    }
}
