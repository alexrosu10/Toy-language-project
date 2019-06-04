package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;

public class IfStm implements IStatement {
    private IExpression ex;
    private IStatement s1;
    private IStatement s2;

    public IfStm(IExpression ex,IStatement s1,IStatement s2){
        this.ex=ex;
        this.s1=s1;
        this.s2=s2;
    }

    public ProgramState execute(ProgramState ps) throws StatementException {
        try{
            if(this.ex.evaluate(ps.getSymTable().top(),ps.getHeap())==0){
                ps.getExeStack().push(this.s1);
            }else {
                ps.getExeStack().push(this.s2);
            }
        } catch (ExpressionException e) {throw new StatementException(e.toString());}

        return null;
    }

    public String toString(){
        String s = "";
        s+="If ";
        s+=ex.toString();
        s+=" then ";
        s+=s1.toString();
        s+=" else ";
        s+=s2.toString();
        s+=";";
        return s;
    }
}
