package Model.Statement;

import Model.ProgramState;
import Model.Utils.Heap;
import Model.Utils.MyDictionary;

public class WriteHeap implements IStatement{
    private String varName;
    private Integer val;

    public WriteHeap(String varName,Integer val){
        this.varName=varName;
        this.val=val;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        if(!ps.getSymTable().top().contains(varName)){
            throw new StatementException("Invalid variable name!");
        }
        else{
            Integer key=ps.getSymTable().top().get(varName);
            if(!ps.getHeap().contains(key)){
                throw new StatementException("Invalid heap key!");
            }
            else {
                ps.getHeap().update(key, val);
                return null;
            }
        }
    }

    @Override
    public String toString() {
        String s="wH("+varName+","+Integer.toString(val)+");";
        return s;
    }
}
