package Model.Expression;

import Model.ProgramState;
import Model.Statement.StatementException;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class ReadHeap implements IExpression{
    private String varName;
    public ReadHeap(String varName){
        this.varName=varName;
    }
    @Override
    public int evaluate(MyDictionary<String, Integer> st,Heap heap) throws ExpressionException{
        if(!st.contains(varName)){
            throw new ExpressionException("Invalid variable name!");
        }
        else{
            Integer key=st.get(varName);
            if(!heap.contains(key)){
                throw new ExpressionException("Invalid heap key!");
            }
            else return heap.get(key);
        }
    }

    @Override
    public String toString() {
        String s="rH("+varName+")";
        return s;
    }
}
