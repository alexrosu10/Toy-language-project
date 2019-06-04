package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class ArithExp implements IExpression {
    private IExpression op1, op2;
    private char operator;

    public ArithExp(IExpression op1, IExpression op2, char operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExpressionException {
        int firstRes = this.op1.evaluate(st,heap);
        int secondRes = this.op2.evaluate(st,heap);

        switch (this.operator) {
            case '+':
                return firstRes + secondRes;

            case '-':
                return firstRes - secondRes;

            case '*':
                return firstRes * secondRes;

            case '/':
                if (secondRes == 0) throw new ExpressionException("Cannot divide by zero!");

                return firstRes / secondRes;

            default:
                throw new ExpressionException("Invalid!");
        }
    }
    public String toString(){
        String s="";
        s+=op1.toString()+operator+op2.toString();
        return s;
    }
}
