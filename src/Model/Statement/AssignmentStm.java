package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;

public class AssignmentStm implements IStatement {
    private String varName;
    private IExpression expr;

    public AssignmentStm(String varName, IExpression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    public ProgramState execute(ProgramState ps) throws StatementException {
        try{
            if(!ps.getSymTable().top().contains(varName)) {
                ps.getSymTable().top().put(this.varName, this.expr.evaluate(ps.getSymTable().top(), ps.getHeap()));
            }
            else{
                ps.getSymTable().top().update(this.varName,this.expr.evaluate(ps.getSymTable().top(),ps.getHeap()));
            }
        } catch (ExpressionException e)
                {throw new StatementException(e.toString());}
        return null;
    }

    public String toString(){
        String s="";
        s+=varName;
        s+="=";
        s+=expr.toString();
        s+=";";
        return s;
    }
}
