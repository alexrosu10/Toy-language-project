package Model.Statement;

import Model.Expression.ExpressionException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;

import java.util.ArrayList;

public class CallStm implements IStatement {
    private String procName;
    private ArrayList<IExpression> params;

    public CallStm(String procName, ArrayList<IExpression> params){
        this.procName=procName;
        this.params=params;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        try {
            if (!ps.getProcTable().contains(procName)) {
                throw new StatementException("No such procedure");
            } else {
                MyDictionary<String, Integer> newSymTable = new MyDictionary<>();
                Integer val;
                ArrayList<String> formalParams = ps.getProcTable().get(procName).x;
                for (int i = 0; i < params.size(); i++) {
                    val = params.get(i).evaluate(ps.getSymTable().top(), ps.getHeap());
                    newSymTable.put(formalParams.get(i), val);
                }
                ps.getSymTable().push(newSymTable);
                ps.getExeStack().push(new ReturnStm());
                ps.getExeStack().push(ps.getProcTable().get(procName).y);
            }
            return null;
        }catch (ExpressionException ee){throw new StatementException(ee.toString());}
    }

    public String toString(){
        String s="";
        s+="call(";
        s+=procName;
        for(IExpression str:params){
            s+=str.toString();
            s+=",";
        }
        s+=");";
        return s;
    }
}
