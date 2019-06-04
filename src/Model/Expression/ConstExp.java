package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class ConstExp implements IExpression {
    private int value;
    public ConstExp(int val){
        value = val;
    }
    public int evaluate(MyDictionary<String, Integer> st, Heap heap) {
        return this.value;
    }
    public String toString(){
        return Integer.toString(value);
    }
}
