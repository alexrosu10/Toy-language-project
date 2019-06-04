package Model.Expression;

public class ExpressionException extends Exception {
    private String text;

    ExpressionException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
