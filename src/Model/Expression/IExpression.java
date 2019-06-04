package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public interface IExpression {
    int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExpressionException;
    String toString();
}
