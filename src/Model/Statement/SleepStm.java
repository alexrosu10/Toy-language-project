package Model.Statement;

import Model.ProgramState;

public class SleepStm implements IStatement {
    private Integer time;

    public SleepStm(Integer time){
        this.time=time;
    }

    @Override
    public ProgramState execute(ProgramState ps){
        if(this.time==0){
            return null;
        }
        else{
            ps.getExeStack().push(new SleepStm(this.time-1));
        }
        return null;
    }

    public String toString(){
        String s="";
        s+="sleep(";
        s+=this.time.toString();
        s+=");";
        return s;
    }
}
