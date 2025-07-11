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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((string == null) ? 0 : string.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeterminedString other = (DeterminedString) obj;
        if (!string.equals(other.string))
            return false;
        return true;
    }
}
