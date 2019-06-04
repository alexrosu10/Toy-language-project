package Model.Statement;

import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;
import Model.Utils.ProcTable;

public class ForkStm implements IStatement {
    private IStatement stm;

    public ForkStm(IStatement stm){
        this.stm=stm;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        MyStack<IStatement> exeStack=new MyStack<>();
        exeStack.push(stm);
        MyStack<MyDictionary<String,Integer>> symTable=new MyStack<>();
        symTable.push(new MyDictionary<>(ps.getSymTable().top().getContent()));
        return new ProgramState(ps.getId()+1,exeStack,ps.getOutput(),symTable,ps.getFileTable(),null,ps.getHeap(),ps.getProcTable());
    }

    @Override
    public String toString() {
        String s="fork(";
        s+=stm.toString();
        s+=");";
        return s;
    }
}
