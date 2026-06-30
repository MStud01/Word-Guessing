package model;

import java.util.Random;

// TODO: COnsider not making this a singleton class as there doesn't seem to be a reason to need this to be one 
// A singleton class representing a random string generator that
// creates strings from randomly generated chars
public class RSG {
    private static RSG rsg;
    private Random rand = new Random();
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
        String generated = "";
        generated += (char) (rand.nextInt(26) + 'A');
        
        for (int i = 1; i < n; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            generated += c;
        }
        
        setGenerated(generated);
        return getGeneratedString();
    }

    public static RSG getInstance() {
        if (rsg == null) {
            rsg = new RSG();
        } 
        return rsg;
    }

    public void generateNewSeed() {
        this.rand = new Random();
    }

    private void setGenerated(String generatedString) {
        this.generatedString = generatedString;
    }

    private String getGeneratedString() {
        return generatedString;
    }
}
