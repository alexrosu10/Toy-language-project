package Model.Statement;

public class StatementException extends Exception {
    private String text;

    StatementException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
