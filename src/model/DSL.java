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
    }

    public static DSL getInstance() {
        return dsl;
    }

    public void addDS(DeterminedString ds) {
        determinedStrings.add(ds);
    }

    // EFFECTS: returns the previously added determind string
    public DeterminedString getDS() {
        return null;
    }

    // EFFECTS: returns the determind string that matches the given string 
    public DeterminedString getDS(String string) {
        return null;
    }

    // EFFECTS: returns the word status of the determind string
    //  that matches the given string 
    public boolean getDSstatus(String string) {
        return false;
    }
}
