package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class BoolExp implements IExpression{
    private IExpression op1, op2;
    private String operator;

    public BoolExp(IExpression op1, IExpression op2, String operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExpressionException {
        int firstRes = this.op1.evaluate(st,heap);
        int secondRes = this.op2.evaluate(st,heap);

        switch (this.operator) {
            case "<":
                if(firstRes<secondRes)
                    return 1;
                else
                    return 0;

            case ">":
                if(firstRes>secondRes)
                    return 1;
                else
                    return 0;

            case "<=":
                if(firstRes<=secondRes)
                    return 1;
                else
                    return 0;

            case ">=":
                if(firstRes>=secondRes)
                    return 1;
                else
                    return 0;
            case "==":
                if(firstRes!=secondRes)
                    return 1;
                else
                    return 0;

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
