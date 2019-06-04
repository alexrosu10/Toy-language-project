package Controller;

public class ControllerException extends Exception {
    private String text;

    ControllerException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
