package Model.Utils;

import javafx.beans.property.SimpleStringProperty;

public class StStPair {
    private SimpleStringProperty st1;
    private SimpleStringProperty st2;

    public StStPair(String int1,String int2){
        this.st1=new SimpleStringProperty(int1);
        this.st2=new SimpleStringProperty(int2);
    }


    public String getSt1() {
        return st1.get();
    }

    public SimpleStringProperty st1Property() {
        return st1;
    }

    public void setSt1(String st1) {
        this.st1.set(st1);
    }

    public String getSt2() {
        return st2.get();
    }

    public SimpleStringProperty st2Property() {
        return st2;
    }

    public void setSt2(String st2) {
        this.st2.set(st2);
    }
}
