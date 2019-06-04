package View;

import Controller.ControllerException;
import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.VarExp;
import Model.Statement.*;
import Model.Utils.MyList;

import java.util.Scanner;

public class UI {
    private InterpreterController cont=new InterpreterController("C:\\Users\\Printu Marnii\\Desktop\\hw2\\src\\logFile.txt");
    private static Scanner scanner=new Scanner(System.in);
    static public final String WITH_DELIMITER = "((?<=-)|(?=-)|(?<=/)|(?=/)|(?<=\\*)|(?=\\*)|(?<=\\+)|(?=\\+))";

    public UI(){}
    public ArithExp breakAssStm(String cmd){
        final String[] aEach = cmd.split(String.format(WITH_DELIMITER, ";"));
        char c=aEach[1].charAt(0);
        ArithExp ae=this.cont.makeArithExp(null,aEach[0],aEach[2],c);
        for(int i=3;i<aEach.length-2;i++){
            c=aEach[i+1].charAt(0);
            ae=this.cont.makeArithExp(ae,null,aEach[i+2],c);
        }
        return ae;
    }
    public PrintStm createPrintStm(String n){
        n=n.replace("Print(","");
        n=n.replace(")","");
        try
        {
            int d = Integer.parseInt(n);
            return new PrintStm(new ConstExp(d));
        }
        catch(NumberFormatException nfe)
        {
            return new PrintStm(new VarExp(n));
        }
    }
    public AssignmentStm createAssStm(String n){
        String[] cmd= n.split("=");
        if(cmd[1].contains("+") || cmd[1].contains("-") || cmd[1].contains("/") || cmd[1].contains("*")){
            ArithExp ae = breakAssStm(cmd[1]);
            return new AssignmentStm(cmd[0],ae);
        }
        else{
            try {
                int c=Integer.parseInt(cmd[1]);
                return new AssignmentStm(cmd[0],new ConstExp(c));
            }catch (NumberFormatException nf){
                return new AssignmentStm(cmd[0],new VarExp(cmd[1]));
            }
        }
    }

    public void whatStatement(String com) throws ViewException {
        if(com.contains("If ")){
            com=com.replace("If ","");
            String[] cmd=com.split(" then ");
            try {
                int c=Integer.parseInt(cmd[0]);
                ConstExp ce=new ConstExp(c);
                String[] n=cmd[1].split(" else ");
                if(n[0].contains("Print")) {
                    PrintStm ps = createPrintStm(n[0]);
                    if(n[1].contains("Print")) {
                        PrintStm ps1 = createPrintStm(n[1]);
                        this.cont.addIfStm(new IfStm(ce,ps,ps1));
                    }
                    else if(n[1].contains("=")){
                        AssignmentStm as1=createAssStm(n[1]);
                        this.cont.addIfStm(new IfStm(ce,ps,as1));
                    }
                }
                else if(n[0].contains("=")){
                    AssignmentStm as=createAssStm(n[0]);
                    if(n[1].contains("Print")) {
                        PrintStm ps1 = createPrintStm(n[1]);
                        this.cont.addIfStm(new IfStm(ce,as,ps1));
                    }
                    else if(n[1].contains("=")){
                        AssignmentStm as1=createAssStm(n[1]);
                        this.cont.addIfStm(new IfStm(ce,as,as1));
                    }
                }
            }catch (NumberFormatException nf){
                VarExp ve=new VarExp(cmd[0]);
                String[] n=cmd[1].split("else");
                if(n[0].contains("Print")) {
                    PrintStm ps = createPrintStm(n[0]);
                    if(n[1].contains("Print")) {
                        PrintStm ps1 = createPrintStm(n[0]);
                        this.cont.addIfStm(new IfStm(ve,ps,ps1));
                    }
                    else if(n[1].contains("=")){
                        AssignmentStm as1=createAssStm(n[1]);
                        this.cont.addIfStm(new IfStm(ve,ps,as1));
                    }
                }
                else if(n[0].contains("=")){
                    AssignmentStm as=createAssStm(n[0]);
                    if(n[1].contains("Print")) {
                        PrintStm ps1 = createPrintStm(n[1]);
                        this.cont.addIfStm(new IfStm(ve,as,ps1));
                    }
                    else if(n[1].contains("=")){
                        AssignmentStm as1=createAssStm(n[1]);
                        this.cont.addIfStm(new IfStm(ve,as,as1));
                    }
                }
            }
        }
        else if(com.contains("Print")){
            com=com.replace("Print(","");
            com=com.replace(")","");
            cont.addPrintStm(com);
        }
        else if(com.contains("=")){
            String[] cmd= com.split("=");
            if(cmd[1].contains("+") || cmd[1].contains("-") || cmd[1].contains("/") || cmd[1].contains("*")){
                ArithExp ae = breakAssStm(cmd[1]);
                cont.addAssStm(cmd[0],ae);
            }
            else{
                try {
                    int c=Integer.parseInt(cmd[1]);
                    cont.addAssStm(cmd[0],new ConstExp(c));
                }catch (NumberFormatException nf){
                    cont.addAssStm(cmd[0],new VarExp(cmd[1]));
                }
            }
        }
        else throw new ViewException("Incorrect statement!");
    }


    public void hardcode(){
        cont.addIfStm(new IfStm(new ConstExp
                        (10),
                new CompoundStm(new AssignmentStm("v", new
                        ConstExp(5)), new PrintStm(new
                        ArithExp( new ConstExp(3),
                        new
                                ConstExp(3), '/'))), new PrintStm
                        (new ConstExp(100))));

            MyList<Integer> out;
            out=null;
            System.out.println(out.toString());

    }


    public void chooseType(){
        System.out.println("Type in for user input and hard for hardcoded example");
        String com = scanner.nextLine();
        if(com.contains("hard")){
            this.hardcode();
        }
        else if(com.contains("in")){
            this.readCmd();
        }

    }


    public void readCmd(){
        System.out.println("Input you program:");
        String com = scanner.nextLine();
        String[] cmd=com.split(";");
        for (String aCmd : cmd) {
            try {
                whatStatement(aCmd);
            }
            catch (ViewException ve){
                System.out.println(ve.toString());
            }
        }
            MyList<Integer> out;
            out=null;
            System.out.println(out.toString());
    }
}
