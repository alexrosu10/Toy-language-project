package Model.Statement;

import Model.ProgramState;
import Model.Utils.FileTable;
import Model.Utils.MyDictionary;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    Integer id;
    String var;

    public ReadFile(Integer id,String var){
        this.id=id;
        this.var=var;
    }

    @Override
    public String toString(){
        String s="readFile("+Integer.toString(id)+","+var+");";
        return s;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws StatementException {
        try{
            while(ps.getFileTable().get(id).y.readLine()!=null) {
                Integer i = Integer.parseInt(ps.getFileTable().get(id).y.readLine());
                if(ps.getSymTable().top().contains(var)){
                    ps.getSymTable().top().update(var,i);
                }
                else ps.getSymTable().top().put(var,i);
            }
            return null;
        }catch(IOException ioe){throw new StatementException(ioe.toString());}
    }
}
