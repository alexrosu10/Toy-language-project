package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;



public class NewStm implements IStatement {
    private String varName;
    private IExpression expr;

    public NewStm(String varName,IExpression expr){
        this.varName=varName;
        this.expr=expr;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        try {
            if (ps.getSymTable().top().contains(this.varName)) {
                ps.getHeap().put(expr.evaluate(ps.getSymTable().top(),ps.getHeap()));
                ps.getSymTable().top().update(this.varName,ps.getHeap().getKey());
            }
            else{
                ps.getHeap().put(expr.evaluate(ps.getSymTable().top(),ps.getHeap()));
                ps.getSymTable().top().put(this.varName,ps.getHeap().getKey());
            }
            return null;
        }catch(ExpressionException ee){throw new StatementException(ee.toString());}
    }

    @Override
    public String toString() {
        String s="new("+varName+","+expr.toString()+");";
        return s;
    }
}
