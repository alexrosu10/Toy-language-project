package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IList;
import Model.Utils.MyDictionary;

public class PrintStm implements IStatement {
    private IExpression exp;

    public PrintStm(IExpression exp) {
        this.exp = exp;
    }

    public ProgramState execute(ProgramState ps) throws StatementException {
        try{
            ps.getOutput().add(this.exp.evaluate(ps.getSymTable().top(),ps.getHeap()));
        } catch (ExpressionException e) {throw new StatementException(e.toString());}
        return null;
    }

    public String toString(){
        String s="";
        s+="Print(";
        s+=exp.toString();
        s+=");";
        return s;
    }
}
