package View;

import Controller.ControllerException;
import Controller.InterpreterController;
import Model.Utils.MyList;

public class RunExample extends Command {
    private InterpreterController ctr;

    public RunExample(String key, String desc,InterpreterController ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    @Override
    public void execute() {
        try {
            MyList<Integer> out;
            out=ctr.allSteps();
            System.out.println(out.toString());
        }catch (ControllerException ce){System.out.println(ce.toString());}
    }
}