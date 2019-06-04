package Model;

import Model.Statement.IStatement;
import Model.Statement.StatementException;
import Model.Utils.*;

public class ProgramState {
    private MyStack<IStatement> exeStack ;
    private MyList<Integer> output ;
    private MyStack<MyDictionary<String, Integer>> symTable ;
    private FileTable fileTable ;
    private IStatement program;
    private Heap heap;
    private ProcTable procTable;
    private int id;

    public ProgramState(int id,MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        MyStack<MyDictionary<String, Integer>> symTable,
                        FileTable fileTable,
                        IStatement program,
                        Heap heap,
                        ProcTable procTable) {
        this.id=id;
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.fileTable=fileTable;
        this.program = program;
        this.heap=heap;
        this.procTable=procTable;
    }

    public MyStack<MyDictionary<String, Integer>> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyStack<MyDictionary<String, Integer>>
                                    symTable) {
        this.symTable = symTable;
    }

    public FileTable getFileTable(){
        return this.fileTable;
    }

    public void setFileTable(FileTable fileTable){
        this.fileTable=fileTable;
    }

    public MyStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyList<Integer> getOutput() {
        return output;
    }

    public void setOutput(MyList<Integer> output) {
        this.output = output;
    }


    void setProgram(IStatement prg) {
        this.program = prg;
    }

    IStatement getProgram() {
        return this.program;
    }


    public Heap getHeap() { return heap; }

    public void setHeap(Heap heap) { this.heap = heap; }

    public boolean isNotCompleted(){return !this.exeStack.isEmpty();}

    public ProgramState oneStep() throws ProgramStateException {
        try {
            if (exeStack.isEmpty()) throw new ProgramStateException("ExeStack is empty!");
            IStatement stm = exeStack.pop();
            return stm.execute(this);
        }catch (StatementException se){throw new ProgramStateException(se.toString());}
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProcTable getProcTable() {
        return procTable;
    }

    public void setProcTable(ProcTable procTable) {
        this.procTable = procTable;
    }
}
