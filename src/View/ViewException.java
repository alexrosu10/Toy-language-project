package View;

public class ViewException extends Exception {
    private String text;

    ViewException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
