package Model;

public class ProgramStateException extends Exception {
    private String text;

    ProgramStateException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
