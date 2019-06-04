package Model.Statement;

import Model.ProgramState;
import Model.Utils.MyStack;

public class CompoundStm implements IStatement {
    private IStatement st1, st2;

    public CompoundStm(IStatement st1,IStatement st2){
        this.st1=st1;
        this.st2=st2;
    }
    public ProgramState execute(ProgramState ps) {
        ps.getExeStack().push(this.st2);
        ps.getExeStack().push(this.st1);

        return null;
    }

    public String toString(){
        String s="";
        s+=st1.toString();
        s+=st2.toString();
        return s;
    }
}
