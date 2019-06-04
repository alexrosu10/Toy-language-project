package Model.Statement;

import Model.ProgramState;

public class ReturnStm implements IStatement{
    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        ps.getSymTable().pop();
        return null;
    }

    public String toString(){
        return "return;";
    }
}
