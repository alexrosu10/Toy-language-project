package Model.Utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IntStringPair {
    private SimpleStringProperty st;
    private SimpleIntegerProperty in;

    public IntStringPair(Integer in,String st){
        this.in=new SimpleIntegerProperty(in);
        this.st=new SimpleStringProperty(st);
    }

    public IntStringPair(String st,Integer in){
        this.in=new SimpleIntegerProperty(in);
        this.st=new SimpleStringProperty(st);
    }

    public String getSt() {
        return st.get();
    }

    public void setSt(String st) {
        this.st.set(st);
    }

    public Integer getIn() {
        return in.get();
    }

    public void setIn(Integer in) {
        this.in.set(in);
    }

}
