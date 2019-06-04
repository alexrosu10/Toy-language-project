package View;

import Controller.ControllerException;
import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.IExpression;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.util.*;

public class Gui {
    private InterpreterController c1;
    private InterpreterController c2;
    private InterpreterController c6;
    private InterpreterController c7;
    private InterpreterController c8;
    private CompoundStm cs1;
    private IfStm is1;
    private CompoundStm cs6;
    private CompoundStm cs7;
    private CompoundStm cs8;
    private List<ProgramState> prgList;
    private InterpreterController currentController;
    public Gui(){
        c1=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        cs1=new CompoundStm(new OpenRFile(1,"C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in"),
                new CompoundStm(new ReadFile(1,"c"),
                        new CompoundStm(new PrintStm(new VarExp("c")),
                                new CompoundStm(new IfStm(new VarExp("c"),
                                        new CompoundStm(new ReadFile(1,"c"),new PrintStm(new VarExp("c"))),new PrintStm(new ConstExp(0))),
                                        new CloseRFile(1)))));
        c1.addCompoundStatement(cs1);

        c2=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        is1=new IfStm(new ConstExp
                (10),
                new CompoundStm(new AssignmentStm("v", new
                        ConstExp(5)), new PrintStm(new
                        ArithExp( new ConstExp(3),
                        new
                                ConstExp(3), '/'))), new PrintStm
                (new ConstExp(100)));
        c2.addIfStm(is1);

        c6=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        cs6=new CompoundStm(new CompoundStm(
                new AssignmentStm("v",new ConstExp(2)),new ForkStm(
                new CompoundStm(new AssignmentStm("v",new ConstExp(3)),new PrintStm(new VarExp("v")))
        )),new PrintStm(new VarExp("v")));
        c6.addCompoundStatement(cs6);

        c7=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        cs7=new CompoundStm(
                new AssignmentStm("v",new ConstExp(10)), new CompoundStm(
                        new ForkStm(
                                new CompoundStm(
                                        new AssignmentStm("v",new ArithExp(new VarExp("v"),new ConstExp(1),'-')),
                                        new CompoundStm(
                                                new AssignmentStm("v",new ArithExp(new VarExp("v"),new ConstExp(1),'-')),
                                                        new PrintStm(new VarExp("v"))
                                        ))),
                new CompoundStm(
                        new SleepStm(10),new PrintStm(new ArithExp(new VarExp("v"),new ConstExp(10),'*'))
                )
        ));
        c7.addCompoundStatement(cs7);

        c8=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");


        ArrayList<IExpression> al1=new ArrayList<>();
        al1.add(new ArithExp(new VarExp("v"),new ConstExp(10),'*'));
        al1.add(new VarExp("w"));

        ArrayList<IExpression>al2=new ArrayList<>();
        al2.add(new VarExp("v"));
        al2.add(new VarExp("w"));

        cs8=new CompoundStm(new AssignmentStm("v",new ConstExp(2)),new CompoundStm(
                new AssignmentStm("w",new ConstExp(5)),new CompoundStm(
                        new CallStm("sum",al1),new CompoundStm(
                                new PrintStm(new VarExp("v")),
                                        new ForkStm(new CompoundStm(
                                                new CallStm("product",al2),new ForkStm(new CallStm("sum",al2))
                                        ))
        )
        )
        )
        );
        c8.addCompoundStatement(cs8);
        ArrayList<String> params1=new ArrayList<>();
        params1.add("a");
        params1.add("b");
        CompoundStm comSt1=new CompoundStm(new AssignmentStm("v",new ArithExp(new VarExp("a"),new VarExp("b"),'+')),
                new PrintStm(new VarExp("v")));
        ArrayList<String> params2=new ArrayList<>();
        params2.add("a");
        params2.add("b");
        CompoundStm comSt2=new CompoundStm(new AssignmentStm("v",new ArithExp(new VarExp("a"),new VarExp("b"),'*')),
                new PrintStm(new VarExp("v")));
        try {
            c8.addProcedure("sum", params1, comSt1);
            c8.addProcedure("product", params2, comSt2);
        }catch (ControllerException ce){System.out.println(ce.toString());}
    }
    @FXML
    private TableView<StStPair> ProcedureTable;

    @FXML
    private TableColumn<StStPair,String> ProcName;

    @FXML
    private TableColumn<StStPair,String> ProcBody;

    @FXML
    private ListView<String> lis;

    @FXML
    private TextField nrOfPrgStField;

    @FXML
    private TableView<NewPair> heapTable;

    @FXML
    private TableColumn<NewPair,Integer> addCol;

    @FXML
    private TableColumn<NewPair,Integer> heapValCol;

    @FXML
    private ListView<String> outList;

    @FXML
    private TableView<IntStringPair> fileTable;

    @FXML
    private TableColumn<IntStringPair,Integer> identCol;

    @FXML
    private TableColumn<IntStringPair,String> fNameCol;

    @FXML
    private TableView<IntStringPair> symTable;

    @FXML
    private TableColumn<IntStringPair,String> vNameCol;

    @FXML
    private TableColumn<IntStringPair,Integer> symValCol;

    @FXML
    private ListView<String> exeStack;

    @FXML
    private ListView<String> prgStateIds;

    @FXML
    public void initialize() {
        ProcName.setCellValueFactory(new PropertyValueFactory<>("st1"));
        ProcBody.setCellValueFactory(new PropertyValueFactory<>("st2"));
        addCol.setCellValueFactory(new PropertyValueFactory<>("int1"));
        heapValCol.setCellValueFactory(new PropertyValueFactory<>("int2"));
        identCol.setCellValueFactory(new PropertyValueFactory<>("in"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        vNameCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        symValCol.setCellValueFactory(new PropertyValueFactory<>("in"));
        ObservableList<String> items = FXCollections.observableArrayList (
                cs1.toString(),is1.toString(),cs6.toString(),cs7.toString(),cs8.toString());
        lis.setItems(items);
    }

    public void setController(){
        String s=lis.getSelectionModel().getSelectedItem();
        if(s.equals(cs1.toString())){
            currentController=c1;
        }
        else if(s.equals(is1.toString())){
            currentController=c2;
        }
        else if(s.equals(cs6.toString())){
            currentController=c6;
        }
        else if(s.equals(cs7.toString())){
            currentController=c7;
        }
        else if(s.equals(cs8.toString())){
            currentController=c8;
        }
        else {
            currentController=null;
        }
    }

    public void selectedProgram(){
        if(currentController!=null) {
            if(prgStateIds.getSelectionModel().getSelectedItem()!=null) {
                Integer id = Integer.parseInt(prgStateIds.getSelectionModel().getSelectedItem());
                if (prgList.size() != 0) {
                    ProgramState currentProgram = currentController.getRepo().getProgram(id);
                    if (currentProgram != null) {
                        Set<Map.Entry<String, Integer>> st = new HashMap<>(currentProgram.getSymTable().top().getContent()).entrySet();
                        ArrayList<IntStringPair> arlis = new ArrayList<>();
                        for (Map.Entry<String, Integer> ent : st) {
                            arlis.add(new IntStringPair(ent.getKey(), ent.getValue()));
                        }
                        ObservableList<IntStringPair> itemis = FXCollections.observableArrayList(
                                arlis);
                        symTable.setItems(itemis);

                        Stack<IStatement> stack=currentProgram.getExeStack().getStack();
                        ArrayList<String> al=new ArrayList<>();
                        for(IStatement t:stack){
                            al.add(t.toString());
                        }
                        ObservableList<String> items = FXCollections.observableArrayList(
                                al);
                        exeStack.setItems(items);
                    }
                }
                else {
                    symTable.getItems().clear();
                    exeStack.getItems().clear();
                }
            }
        }
    }

    public void oneStep(){
        if(currentController!=null) {
            try {
                prgList = currentController.removeCompletedPrg(currentController.getRepo().getPrgList());
                if (prgList.size() != 0) {
                    currentController.oneStepForAllPrg(prgList);
                    nrOfPrgStField.setText(currentController.nrOfPrgStates().toString());
                    MyList<Integer> out = currentController.getRepo().getPrgList().get(0).getOutput();
                    ObservableList<String> items = FXCollections.observableArrayList(
                            out.toString());
                    outList.setItems(items);
                    int i = prgList.size();
                    ArrayList<String> idlis = new ArrayList<>();
                    while (i > 0) {
                        idlis.add(prgList.get(i - 1).getId().toString());
                        i -= 1;
                    }
                    items = FXCollections.observableArrayList(idlis);
                    prgStateIds.setItems(items);
                    prgList = currentController.removeCompletedPrg(currentController.getRepo().getPrgList());

                    if(prgList.size()!=0) {
                        Set<Map.Entry<Integer, Integer>> heap = new HashMap<>(prgList.get(prgList.size() - 1).getHeap().getContent()).entrySet();
                        ArrayList<NewPair> arlis = new ArrayList<>();
                        for (Map.Entry<Integer, Integer> ent : heap) {
                            arlis.add(new NewPair(ent.getKey(), ent.getValue()));
                        }
                        ObservableList<NewPair> itemis = FXCollections.observableArrayList(
                                arlis);
                        heapTable.setItems(itemis);
                    }

                    if(prgList.size()!=0) {
                        Set<Map.Entry<Integer, Pair<String, BufferedReader>>> ft = new HashMap<>(prgList.get(prgList.size() - 1).getFileTable().getContent()).entrySet();
                        ArrayList<IntStringPair> arlis = new ArrayList<>();
                        for (Map.Entry<Integer, Pair<String, BufferedReader>> ent : ft) {
                            arlis.add(new IntStringPair(ent.getKey(), ent.getValue().x));
                        }
                        ObservableList<IntStringPair> itemis = FXCollections.observableArrayList(
                                arlis);
                        fileTable.setItems(itemis);
                    }

                    if(prgList.size()!=0) {
                        Set<Map.Entry<String,Pair<ArrayList<String>,IStatement>>> pt = new HashMap<>(prgList.get(prgList.size() - 1).getProcTable().getContent()).entrySet();
                        ArrayList<StStPair> arlis = new ArrayList<>();
                        for (Map.Entry<String,Pair<ArrayList<String>,IStatement>> ent : pt) {
                            String s="(";
                            for (String el:ent.getValue().x){
                                s+=el;
                                s+=" ";
                            }
                            s+="); ";
                            s+=ent.getValue().y.toString();
                            arlis.add(new StStPair(ent.getKey(), s));
                        }
                        ObservableList<StStPair> itemis = FXCollections.observableArrayList(
                                arlis);
                        ProcedureTable.setItems(itemis);
                    }


                } else {
                    nrOfPrgStField.setText("0");
                    prgStateIds.getItems().clear();
                    outList.getItems().clear();
                    fileTable.getItems().clear();
                    heapTable.getItems().clear();
                    symTable.getItems().clear();
                    exeStack.getItems().clear();
                    ProcedureTable.getItems().clear();
                    currentController.shutDownExecutor();
                }
            } catch (ControllerException ce) {
                System.out.println(ce.toString());
            }
        }
        else{
            System.out.println("Bad!");
        }

    }


}
