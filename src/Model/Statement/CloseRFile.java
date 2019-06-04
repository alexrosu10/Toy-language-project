package Model.Statement;

import Model.ProgramState;
import Model.Utils.FileTable;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private Integer id;
    public CloseRFile(Integer id){
        this.id=id;
    }

    @Override
    public String toString(){
        String s="closeRFile("+Integer.toString(id)+");";
        return s;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        try {
            ps.getFileTable().get(id).y.close();
            ps.getFileTable().remove(id);
            ps.setFileTable(ps.getFileTable());
            return null;
        }catch (IOException ioe){throw new StatementException(ioe.toString());}
    }
}
