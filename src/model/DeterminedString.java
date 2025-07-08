package model;

// Represents a string with a well-determind word status where the status
// determines if the string is a word or not
public class DeterminedString {
    private String string;
    private boolean status;

    public DeterminedString() {
        string = "";
        status = false;
    }

    public DeterminedString(String string, boolean status) {
        this.string = string;
        setStatus(status);
    }

    public String getString() {
        return string;
    }

    public boolean isWord() {
        return status;
    }

    public boolean isNotWord() {
        return !(status);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
