package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyStack;

public class WhileStm implements IStatement {
    private IStatement st;
    private IExpression ie;

    public WhileStm(IExpression ie,IStatement st){
        this.st=st;
        this.ie=ie;
    }
    public ProgramState execute(ProgramState ps) throws StatementException {
        try {
            if (ie.evaluate(ps.getSymTable().top(), ps.getHeap())!=0){
                ps.getExeStack().push(this);
                ps.getExeStack().push(this.st);
            }
        }catch (ExpressionException ee){throw new StatementException(ee.toString());}
        return null;
    }

    public String toString(){
        String s="while(";
        s+=ie.toString()+") do (";
        s+=st.toString()+");";
        return s;
    }
}
