package Model.Utils;

import javafx.beans.property.SimpleIntegerProperty;

public class NewPair {

    private SimpleIntegerProperty int1;
    private SimpleIntegerProperty int2;

    public NewPair(Integer int1,Integer int2){
        this.int1=new SimpleIntegerProperty(int1);
        this.int2=new SimpleIntegerProperty(int2);
    }


    public Integer getInt1() {
        return int1.get();
    }

    public void setInt1(Integer int1) {
        this.int1.set(int1);
    }

    public Integer getInt2() {
        return int2.get();
    }

    public void setInt2(Integer int2) {
        this.int2.set(int2);
    }
}
