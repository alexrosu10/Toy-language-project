package Repository;

public class RepoException extends Exception {
    private String text;

    RepoException(String txt) {
        text=txt;
    }
    public String toString(){
        return text;
    }
}
