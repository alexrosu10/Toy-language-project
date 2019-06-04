package Repository;

import Model.Expression.ArithExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    private ArrayList<ProgramState> repo=new ArrayList<>();
    private String logFilePath;

    public Repo(String logFilePath){
        this.logFilePath=logFilePath;
    }

    public ArrayList<ProgramState> getPrgList(){
        return this.repo;
    }

    public void setPrgList(List<ProgramState> al){
        this.repo=new ArrayList<>(al);
    }

    public void addProc(String procName, ArrayList<String> params,IStatement stm) throws RepoException {
        try{
        ProcTable procTable=this.repo.get(this.repo.size()-1).getProcTable();
        Pair<ArrayList<String>,IStatement> pair=new Pair<>(params,stm);
        procTable.put(procName,pair);
        this.repo.get(this.repo.size()-1).setProcTable(procTable);
        }catch (Exception e){throw new RepoException(e.getMessage());}
    }

    public void addCompoundStatement(CompoundStm cstm){
        MyStack<IStatement> temp = this.repo.get(this.repo.size()-1).getExeStack();
        temp.push(cstm);
        this.repo.get(this.repo.size()-1).setExeStack(temp);
    }
    public void addIfStm(IfStm ifs){
        MyStack<IStatement> temp = this.repo.get(this.repo.size()-1).getExeStack();
        temp.push(ifs);
        this.repo.get(this.repo.size()-1).setExeStack(temp);
    }
    public void addAssStm(AssignmentStm as){
        MyStack<IStatement> temp = this.repo.get(this.repo.size()-1).getExeStack();
        temp.push(as);
        this.repo.get(this.repo.size()-1).setExeStack(temp);
    }
    public void addPrintStm(PrintStm pstm){
        MyStack<IStatement> temp = this.repo.get(this.repo.size()-1).getExeStack();
        temp.push(pstm);
        this.repo.get(this.repo.size()-1).setExeStack(temp);
    }
    public void addProgramState(ProgramState prgState){
        this.repo.add(prgState);
    }

    public Integer nrOfPrgStates(){return this.repo.size();}

    public ProgramState getProgram(Integer id){
        ProgramState pr=null;
        for(ProgramState prg:this.repo){
            if(prg.getId().equals(id)){
                pr=prg;
            }
        }
        return pr;
    }
    @Override
    public void logPrgStateExec(ProgramState ps) throws RepoException{
        try {
            MyStack<IStatement> exeStack=ps.getExeStack();
            MyList<Integer> output = ps.getOutput();
            MyDictionary<String, Integer> symTable = ps.getSymTable().top();
            FileTable fileTable=ps.getFileTable();
            Heap heap=ps.getHeap();
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            pw.append("\nProgram nr ");
            pw.append(Integer.toString(ps.getId()));
            pw.append("\nExeStack\n");
            pw.append(exeStack.toString());
            pw.append("\nSymTable\n");
            pw.append(symTable.toString());
            pw.append("\nOut\n");
            pw.append(output.toString());
            pw.append("\nFileTable\n");
            pw.append(fileTable.toString());
            pw.append("\nHeap\n");
            pw.append(heap.toString());
            pw.append("##################################################################\n");
            pw.close();
        }catch (IOException ioe){throw new RepoException(ioe.toString());}
    }
}
