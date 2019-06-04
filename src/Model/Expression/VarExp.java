package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class VarExp implements IExpression {
    private String name;

    public VarExp(String name) {
        this.name = name;
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExpressionException {
        if (st.contains(this.name)) {
            return st.get(this.name);
        } else {
            throw new ExpressionException("Nonexistent variable!");
        }
    }

    public String toString(){
        return name;
    }
}
