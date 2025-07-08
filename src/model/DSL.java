package model;

import java.util.ArrayList;
import java.util.List;
// import java.util.Set;

// Represents a singleton class representing a list of determined strings
public class DSL {
    private static DSL dsl;
    private List<DeterminedString> determinedStrings;
    // possible to use an array or a set instead (maybe?)
    
    private DSL() {
        determinedStrings = new ArrayList<DeterminedString>();
        dsl = this;
        assert dsl != null;
    }

    public static DSL getInstance() {
        if (dsl == null) {
            return new DSL();
        } else {
            return dsl;
        }
    }

    // REQUIRES: ds != null
    // MODIFIES: this
    // EFFECTS: adds the given determined string to the list if it does not exist
    public void addDS(DeterminedString ds) {
        determinedStrings.add(ds);
        assert determinedStrings.size() > 0;
    }

    // MODIFIES: this
    // EFFECTS: constructs a ds with the given string and not-word status 
    // and adds to the list if it does not exist
    public void addDS(String string) {
        addDS(string, false);
        assert determinedStrings.size() > 0;
    }

    // MODIFIES: this
    // EFFECTS: constructs a ds with the given string and not-word status 
    // and adds to the list if it does not exist
    public void addDS(String string, boolean status) {
        addDS(new DeterminedString(string, status));
        assert determinedStrings.size() > 0;
    }

    // EFFECTS: returns the previously added determind string and null if no 
    //          ds exists in the list
    public DeterminedString getRecentDS() {
        assert determinedStrings != null;
        return null;
    }

    // EFFECTS: returns the determined string that matches the given string 
    //          and null if no ds with the given string exists
    public DeterminedString getDS(String string) {
        assert determinedStrings != null;
        return null;
    }

    // REQUIRES: getDS(string) != null (i.e., the DS with given string exists)
    // EFFECTS: returns the word status of the determind string
    //          that matches the given string 
    public boolean getDSstatus(String string) {
        assert (determinedStrings != null) && (getDS(string) != null);
        return false;
    }

    public List<DeterminedString> getDeterminedStrings() {
        return determinedStrings;
    }
}
