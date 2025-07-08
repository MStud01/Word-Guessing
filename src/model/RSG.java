package model;

import java.util.Random;

// A singleton class representing a random string generator that
// creates strings from randomly generated chars
public class RSG {
    private static RSG rsg;
    private String generatedString;

    private RSG() {
        this.generatedString = "";
        rsg = this;
    }

    private RSG(String string) {
        this.generatedString = string;
        rsg = this;
    }

    // MODIFIES: this
    // EFFECTS: generates a new string randomly of a random length, changes the
    //          generatedString and returns it 
    public String generateString() {
        int randomNum = 0;
        return generateString(randomNum);
    }

    // MODIFIES: this
    // EFFECTS: generates a new string randomly of length n, changes the 
    //          generatedString and returns it 
    public String generateString(int n) {
        String genereatedString = "";
        setGenerated(generatedString);
        return genereatedString;
    }

    public static RSG getInstance() {
        if (rsg == null) {
            return new RSG();
        } else {
            return rsg;
        }
    }

    private void setGenerated(String generatedString) {
        this.generatedString = generatedString;
    }

    public String getGeneratedString() {
        return generatedString;
    }
}
