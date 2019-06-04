package Controller;

import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.IExpression;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;
import Repository.Repo;
import Repository.RepoException;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class InterpreterController {
    private Repo repo;
    private ProgramState prg=null;
    private String pathname;
    private ExecutorService executor;

    public InterpreterController(String pathname){
        this.repo=new Repo(pathname);
        this.pathname=pathname;
        IStatement prog=null;
        MyStack<MyDictionary<String,Integer>> symTable= new MyStack<>();
        MyDictionary<String,Integer> md=new MyDictionary<>();
        symTable.push(md);
        MyStack<IStatement> exeStack= new MyStack<>();
        MyList<Integer> output=new MyList<>();
        FileTable fileTable=new FileTable();
        Heap heap=new Heap();
        ProcTable procTable=new ProcTable();
        ProgramState newState=new ProgramState(1,exeStack,output,symTable,fileTable,prog,heap,procTable);
        this.repo.addProgramState(newState);
        executor = Executors.newFixedThreadPool(2);
    }

    public Repo getRepo(){
        return this.repo;
    }

    public void addProcedure(String procName, ArrayList<String> params,IStatement stm) throws ControllerException {
        try {
            this.repo.addProc(procName, params, stm);
        }catch (RepoException re){throw new ControllerException(re.toString());}
    }

    public void addCompoundStatement(CompoundStm st1){this.repo.addCompoundStatement(st1);}
    public void addIfStm(IfStm is1){
        this.repo.addIfStm(is1);
    }
    public void addAssStm(String var, IExpression ie){
        this.repo.addAssStm(new AssignmentStm(var,ie));
    }
    public ArithExp makeArithExp(ArithExp ae,String c1,String c2, Character op){
        if(ae==null){
            try{
                int d = Integer.parseInt(c1);
                try{
                    int e=Integer.parseInt(c2);
                    return new ArithExp(new ConstExp(d),new ConstExp(e),op);
                }catch (NumberFormatException n){
                    return new ArithExp(new ConstExp(d),new VarExp(c2),op);
                }
            }
            catch(NumberFormatException nfe)
            {
                try{
                    int e=Integer.parseInt(c2);
                    return new ArithExp(new VarExp(c1),new ConstExp(e),op);
                }catch (NumberFormatException n){
                    return new ArithExp(new VarExp(c1),new VarExp(c2),op);
                }
            }
        }
        else{
            try{
                int d = Integer.parseInt(c2);
                return new ArithExp(ae,new ConstExp(d),op);
            }
            catch(NumberFormatException nfe)
            {
                return new ArithExp(ae,new VarExp(c2),op);
            }
        }
    }
    public void addPrintStm(String st){
        try
        {
            int d = Integer.parseInt(st);
            this.repo.addPrintStm(new PrintStm(new ConstExp(d)));
        }
        catch(NumberFormatException nfe)
        {
            this.repo.addPrintStm(new PrintStm(new VarExp(st)));
        }
    }
    public void addNewPrgState(IStatement prog){
        MyStack<MyDictionary<String,Integer>> symTable= new MyStack<>();
        MyStack<IStatement> exeStack= new MyStack<>();
        MyList<Integer> output=new MyList<>();
        FileTable fileTable=new FileTable();
        Heap heap=new Heap();
        ProcTable procTable=new ProcTable();
        ProgramState newState=new ProgramState(1,exeStack,output,symTable,fileTable,prog,heap,procTable);
        this.repo.addProgramState(newState);
    }

    public Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                                 Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer,Pair<String, BufferedReader>> conservativeFileGarbageCollector(Map<Integer,Pair<String, BufferedReader>> ft) {
            ft.entrySet().stream()
                    .filter(e->ft.keySet().contains(e.getKey()))
                    .forEach(e-> {
                        try {
                            e.getValue().y.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
            ft.clear();
            return ft;
    }

    public List<ProgramState> removeCompletedPrg(ArrayList<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) throws ControllerException{
        try {
            for (ProgramState programState : prgList) {
                    repo.logPrgStateExec(programState);
            }

            List<Callable<ProgramState>> callList = prgList.stream()
                    .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                    .collect(Collectors.toList());
            List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            return null;
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            prgList.addAll(newPrgList);

            for (ProgramState programState : prgList) {
                repo.logPrgStateExec(programState);
            }

            repo.setPrgList(prgList);
        }catch (RepoException | InterruptedException re){throw new ControllerException(re.toString());}
    }

    public MyList<Integer> allSteps() throws ControllerException {
        try {
            List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());
            while (prgList.size() > 0) {
                prgList.get(prgList.size() - 1).getHeap().setContent(conservativeGarbageCollector
                        (prgList.get(prgList.size() - 1).getSymTable().top().getContent().values(), prgList.get(prgList.size() - 1)
                                .getHeap().getContent()));
                oneStepForAllPrg(prgList);
                prgList = removeCompletedPrg(repo.getPrgList());
            }
            executor.shutdownNow();
            MyList<Integer> out = repo.getPrgList().get(0).getOutput();
            List<ProgramState> tmpList = repo.getPrgList();
            repo.getPrgList().get(repo.getPrgList().size() - 1).getFileTable()
                    .setContent(conservativeFileGarbageCollector(tmpList.get(tmpList.size() - 1).getFileTable().getContent()));
            repo.setPrgList(prgList);
            return out;
        }catch (IndexOutOfBoundsException iob){throw new ControllerException("Already done!");}
    }

    public Integer nrOfPrgStates(){return this.repo.nrOfPrgStates();}

    public void shutDownExecutor(){
        executor.shutdownNow();
    }
}
