package View;

import Controller.InterpreterController;
import Model.Expression.*;
import Model.Statement.*;

//fa unit tests boss

public class View {
    public static void main(String[] args) {
        InterpreterController c1=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        CompoundStm cs1=new CompoundStm(new OpenRFile(1,"C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in"),
                new CompoundStm(new ReadFile(1,"c"),
                new CompoundStm(new PrintStm(new VarExp("c")),
                new CompoundStm(new IfStm(new VarExp("c"),
                new CompoundStm(new ReadFile(1,"c"),new PrintStm(new VarExp("c"))),new PrintStm(new ConstExp(0))),
                new CloseRFile(1)))));
        c1.addCompoundStatement(cs1);

        InterpreterController c2=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        IfStm is1=new IfStm(new ConstExp
                (10),
                new CompoundStm(new AssignmentStm("v", new
                        ConstExp(5)), new PrintStm(new
                        ArithExp( new ConstExp(3),
                        new
                                ConstExp(3), '/'))), new PrintStm
                (new ConstExp(100)));
        c2.addIfStm(is1);

        InterpreterController c3=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        CompoundStm cstm1=new CompoundStm(
                new AssignmentStm("v",new ConstExp(10)),new CompoundStm(
                new NewStm("v",new ConstExp(20)),new CompoundStm(
                new NewStm("a",new ConstExp(22)),new CompoundStm(
                new WriteHeap("a",30),new CompoundStm(
                new PrintStm(new VarExp("a")),new CompoundStm(
                new PrintStm(new ReadHeap("a")),new AssignmentStm("a",new ConstExp(0))
        )
        )
        )
        )
        )
        );
        c3.addCompoundStatement(cstm1);

        InterpreterController c4=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        CompoundStm cstm2=new CompoundStm(
                new AssignmentStm("v",new ConstExp(6)),new CompoundStm(
                new WhileStm(new BoolExp(new VarExp("v"),new ConstExp(0),">"), new CompoundStm(
                new PrintStm(new VarExp("v")),new AssignmentStm("v",new ArithExp(new VarExp("v"),
                new ConstExp(1),'-'))
                )),new PrintStm(new VarExp("v"))
        )
        );
        c4.addCompoundStatement(cstm2);

        InterpreterController c5=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        CompoundStm cs5=new CompoundStm(new OpenRFile(1,"C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\test.in"),
                new CompoundStm(new ReadFile(1,"c"),
                new CompoundStm(new PrintStm(new VarExp("c")),
                new IfStm(new VarExp("c"),
                new CompoundStm(new ReadFile(1,"c"),new PrintStm(new VarExp("c"))),new PrintStm(new ConstExp(0)))
                )));
        c5.addCompoundStatement(cs5);

        InterpreterController c6=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
        CompoundStm cs6=new CompoundStm(new CompoundStm(
                new AssignmentStm("v",new ConstExp(2)),new ForkStm(
                new CompoundStm(new AssignmentStm("v",new ConstExp(3)),new PrintStm(new VarExp("v")))
        )),new PrintStm(new VarExp("v")));
        c6.addCompoundStatement(cs6);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",cs1.toString(),c1));
        menu.addCommand(new RunExample("2",is1.toString(),c2));
        menu.addCommand(new RunExample("3",cstm1.toString(),c3));
        menu.addCommand(new RunExample("4",cstm2.toString(),c4));
        menu.addCommand(new RunExample("5",cs5.toString(),c5));
        menu.addCommand(new RunExample("6",cs6.toString(),c6));
        menu.show();
    }
}
