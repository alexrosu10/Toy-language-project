package Model.Statement;

import Model.ProgramState;
import Model.Utils.FileTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement{
    private Integer file_id;
    private String filename;

    public OpenRFile(Integer file_id, String filename){
        this.filename=filename;
        this.file_id=file_id;
    }

    @Override
    public String toString(){
        String s="openRFile("+Integer.toString(file_id)+","+filename+");";
        return s;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        boolean c=true;
        for(Integer i: ps.getFileTable().keySet()){
            if(ps.getFileTable().get(i).x.equals(filename)){
                c=false;
            }
        }
        if(!c) throw new StatementException("File already opened!");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ps.getFileTable().put(file_id,filename,br);
            return null;
        }catch (IOException ioe){throw new StatementException(ioe.toString());}
    }
}
