package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import model.DSL;
import model.RSG;
import model.exceptions.DeterminedStringNotFoundException;
import model.DeterminedString;


// TODO: Add a logging system to log user inputs and game outcomes
// TODO: Add a saving system to save the user's progress and the list of Determined Strings (DSL) to a file

// An application that utilizes the Scanner class to create a command-line
// interface for the Word Guessing Game, allowing the user to  
//                      1) determine whether a set of randomly generated strings is an actual word or not, and
//                      2) add new words to DSL (the list of Determined Strings)

public class WordGuessingGame {
    // private static final String DSLfile = "";
    private static RSG rsg = RSG.getInstance();
    private static DSL dsl = DSL.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static int gameSpeed = 7; // default value is 7, each decrement raises the delay between each action

    public WordGuessingGame() {
        setGameSpeed();
        start();

        // TODO: Revise this block for loading DSL
        // while (true) {
        //     // System.out.println("Loading Determined Strings (DSL)...");
        //     // dsl.loadDSL();
        //     // if (dsl.getDSL().isEmpty()) {
        //     //     System.out.println("No Determined Strings found. Please add new words to DSL.");
        //     //     bootAddMode();
        //     // } else {
        //     //     break;
        //     // }
        //     start();
        // }
    }

    public void printToConsole(String message) {
        for (char c: message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(50/gameSpeed); // gameSpeed is set by user when main() is run
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
            // TImer timer = new Timer(0, null)
        }
        // System.out.println("");
    }

    public void setGameSpeed() {
        printToConsole("Set the game speed from a scale from 1 to 10.\n");
        printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.\n");
        String response = scanner.next();
        printToConsole("\n");
        if (response.matches("^(?:[1-9]|10)$")) {
            gameSpeed = Integer.parseInt(response);
        } else if (response.equalsIgnoreCase("ULTRA")) {
            gameSpeed = 100;
        }
        while (response.toLowerCase().charAt(0) != 's') {
            printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.\n");
            response = scanner.next();
            printToConsole("\n");
            if (response.matches("^(?:[1-9]|10)$")) {
                gameSpeed = Integer.parseInt(response);
            } else if (response.equalsIgnoreCase("ULTRA")) {
                gameSpeed = 100;
            }
        }
    }

    // Boots up the start menu where the user is introduced to the game and the game modes
    // and is prompted in the subsequent function call
    public void start() {
        printToConsole("Welcome to the Word Guessing Game!\n\n\n");
        printToConsole("You can play 3 modes in this game:\n\n");
        printToConsole("Game Mode 1: Guessing Mode\nGuess whether a number of randomly generated string of English letters is a word or not.\n\n");
        printToConsole("Game Mode 2: Adding Mode\nAdd new words to the list of Determined Strings (DSL) - the in-game library\nof strings that verifies whether a string is a word or not.\n\n");
        printToConsole("Game Mode 3: Grouping Mode\nGroup a number of random English words depending on specific features.\n\n\n");
        printToConsole("To play, choose between the above two game modes.\n");
        processInputs();
    }

    // The user is prompted to choose among the previously presented options in start() and
    // the appropriate message is printed and a game mode is booted up through a function call
    // or the program exits according to the user's input, unless the inputs do not match any
    // of the presented options, upon which the user is prompted until the right input
    // has been entered by the user.
    public void processInputs() {
        String input = "";

        while (!input.equalsIgnoreCase("quit")) {
            printToConsole("What game mode do you wish to play?\n");
            input = scanner.next();
            printToConsole("\n");

            if (input.startsWith("1") || input.equalsIgnoreCase("guessing mode") || input.equalsIgnoreCase("guess")) {
                printToConsole("You have chosen the Guessing Mode.\n");
                bootGuessMode();
            } else if (input.startsWith("2") || input.equalsIgnoreCase("adding mode") || input.equalsIgnoreCase("add")) {
                printToConsole("You have chosen the Adding Mode.\n");
                bootAddMode();
            } else if (input.startsWith("3") || input.equalsIgnoreCase("grouping mode") || input.equalsIgnoreCase("group")) {
                printToConsole("You have chosen the Grouping Mode.\n");
                bootGroupMode();
            } else if (!input.equalsIgnoreCase("quit")) {
                printToConsole("That is an invalid choice. Please choose between the provided options.\n");
            }
        }

        printToConsole("CHOICE: QUIT\n\n");
        printToConsole("Thank you for playing the Word Guessing Game!\n");
        printToConsole("Exiting the game now...");
    }

    // TODO: Refactor the following function so that it is more readable and makes calls to multiple functions for readability
    // This function initiates the "Guessing Mode" game mode, initializes the appropriate local
    // variables to hold generated strings and user guesses and prompts the user to make guesses
    // on the word status of the randomly generated strings displayed to them.
    // The function makes calls to multiple functions, some setting up local variables while
    // others prompt the user for making changes to the contents of the DSL and finally
    // provides an end-of-game summary to the user of their performance in the game.
    public void bootGuessMode() {
        rsg.generateNewSeed();
        List<DeterminedString> generatedStrings = new ArrayList<DeterminedString>();
        List<String> addedStrings = new ArrayList<String>(), changedStrings = new ArrayList<String>();
        
        generateAndAddStrings(generatedStrings, addedStrings);

        printToConsole("Here is your set of generated strings:\n");
        int n = generatedStrings.size();
        for (int i = 0; i < n; i++) {
            printToConsole(String.format("%-3s %-20s",Integer.toString(i + 1) + ".", generatedStrings.get(i).getString()) + ((i % 4 == 3) ? "\n" : " "));
        }

        printToConsole("\n\nNow, you can guess whether each string is a word or not.\n");

        boolean changeFlag = false;
        while (n > 0) {
            printToConsole("Choose a string from the generated strings above and type it in to guess.\n");
            printToConsole("Or you can type \"skip\" if you think the remainder of the strings cannot possibly be English words.\n");
            String response = scanner.next();
            printToConsole("\n");
            if (response.equalsIgnoreCase("skip")) {
                break;
            }
            String selectedString = response;
            int DSindex = generatedStrings.indexOf(new DeterminedString(selectedString, false)); 
            if (DSindex == -1) {
                printToConsole("That was not one of the randomly generated strings. Try typing it again.\n");
                continue;
            }
            printToConsole("Do you think this string is a word? Type \"Yes\" if you do think so. Else type \"No\".\n");
            String guess = scanner.next();
            printToConsole("\n");

            DeterminedString ds = generatedStrings.get(DSindex);
            if ((guess.toLowerCase().charAt(0) == 'y') || (guess.toLowerCase().charAt(0) == 'w')) {
                printToConsole("You have guessed that the string is a word.\n");
                ds.setStatus(true);
            } else if (guess.toLowerCase().charAt(0) == 'n') {
                printToConsole("You have guessed that the string is not a word.\n");
                ds.setStatus(false);
            } else {
                printToConsole("That is an invalid choice. Please choose between the provided options.\n");
                continue;
            }

            try {
                if (dsl.getDSstatus(selectedString) == ds.isWord()) {
                    printToConsole("Your guess was correct!!\n\n");
                } else {
                    printToConsole("Unfortunately, uour guess was incorrect...\n");
                    printToConsole("The string you guessed was actually " + (ds.isWord() ? "not a word" : "a word") + ".\n\n");
                    int prevCStrings = changedStrings.size();
                    promptforChangeStatus(selectedString, ds.isWord(), changedStrings);
                    changeFlag = (changedStrings.size() != prevCStrings);
                }
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole("\nERROR: THIS WAS NOT SUPPOSED TO HAPPEN!!!!\n");
                continue;
            }
            if (changeFlag) {
                if (n != 1) {
                    printChangesSummary(generatedStrings, addedStrings, changedStrings);
                }
                changeFlag = false;
            }
            n--;
        }
        
        printFinalSummary(generatedStrings, addedStrings, changedStrings);
        
        printToConsole("Are you satisfied with the above results?\n");
        String response = scanner.next();
        printToConsole("\n");

        while ((response.toLowerCase().charAt(0) == 'n') || (response.toLowerCase().charAt(0) == 'c')) {
            if (changeFlag) {
                printFinalSummary(generatedStrings, addedStrings, changedStrings);
                // OR
                // printChangesSummary(generatedStrings, addedStrings, changedStrings);
                changeFlag = false;
            }
            printToConsole("Please type in the string that you wish to change the status of.\n");
            String selectedString = scanner.next();
            printToConsole("\n");
            if (generatedStrings.indexOf(new DeterminedString(selectedString, false)) == -1) {
                printToConsole("That was an invalid string. Try typing in one of the generated strings below.\n\n");
                printFinalSummary(generatedStrings, addedStrings, changedStrings);
                continue;
            }
            try {
                boolean status = dsl.getDSstatus(selectedString);
                int prevCStrings = changedStrings.size();
                promptforChangeStatus(selectedString, !status, changedStrings);
                changeFlag = (changedStrings.size() != prevCStrings);
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole("\nERROR: THIS WAS NOT SUPPOSED TO HAPPEN!!!!\n");
            } finally {
                printToConsole("Is that all??? If you do still wish to make any more changes, type \"continue\".\n");
                response = scanner.next();
                printToConsole("\n");
            }
            
        }
        printToConsole("That is the end of this round for the Guessing Game Mode.\n\n");
        printToConsole("END OF ROUND SUMMARY:\n");
        printFinalSummary(generatedStrings, addedStrings, changedStrings);
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void generateAndAddStrings(List<DeterminedString> generatedStrings, List<String> addedStrings) {
        Random rng = new Random();
        printToConsole("How large do you should the set of strings be?\n");
        int setSize = scanner.nextInt();
        printToConsole("\n");

        for (int i = 0; i < setSize; i++) {
            // The Idea is not type in a number greater than 20.
            // Type in a string that is not longer than 20 English letters.
            printToConsole("Pick a number between 1 and 20.\n");
            int len = scanner.nextInt();
            String generatedString = rsg.generateString(len);
            printToConsole("\n");
            DeterminedString ds = new DeterminedString(generatedString, false);
            while (generatedStrings.contains(ds)) {
                generatedString = rsg.generateString(len);
                ds = new DeterminedString(generatedString, false);
            }
            generatedStrings.add(ds);
            try {
                dsl.getDSstatus(generatedString);
            } catch (DeterminedStringNotFoundException dsnfe) {
                dsl.addDS(generatedString, rng.nextBoolean());
                addedStrings.add(generatedString);
                printToConsole("The string " + generatedString + " was not found in the in-game collection, and has now been added to the in-game\ncollection with a randomized status.\n\n");
            }
        }
        printToConsole("\n");
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: 
    public void promptforChangeStatus(String selectedString, boolean newStatus, List<String> changedStrings) {
        printToConsole("Would you like to change the status of the string " + selectedString + " to " + (newStatus ? "" : "not ") + "be a word?\n");
        String prompt = scanner.next();
        printToConsole("\n");

        if ((prompt.toLowerCase().charAt(0) == 'y')) {
            printToConsole("You have chosen to change the status of this string.\n");
            dsl.getDS(selectedString).setStatus(newStatus);
            changedStrings.add(selectedString);
        } else if (prompt.toLowerCase().charAt(0) == 'n') {
            printToConsole("You have chosen to not change the status of this string.\n");
        } else {
            printToConsole("That is an invalid choice. The status of this string will not be changed.\n");
        }

        printToConsole("The status of the string " + selectedString + " can be changed at the end of the game or in the second game mode.\n\n");
    }

    // This function prints a summary of the guesses made by the user as changes are made to 
    // the DSL (the in-game library), particularly relating to the strings generated this 
    // round, whether if one of them was recently added or had their word status changed.
    public void printChangesSummary(List<DeterminedString> generatedStrings, List<String> addedStrings, List<String> changedStrings) {
        for (int i = 0; i < generatedStrings.size(); i++) {
            DeterminedString ds = generatedStrings.get(i);
            printToConsole((i + 1) + ". " + ds.getString() + "\n");
            printToConsole("Your Guess - "+ (ds.isWord() ? "A Word" : "Not A Word") +"\n");
            if (addedStrings.contains(ds.getString())) {
                printToConsole("This string was recently added into the in-game library in this round.\n");
            }
            if (changedStrings.contains(ds.getString())) {
                try {
                    printToConsole("The status of the string was changed from " + (dsl.getDSstatus(ds.getString()) ? "not a word to a word" : "a word to not a word") +"\n");
                } catch (DeterminedStringNotFoundException dsnfe) {
                    printToConsole("\nERROR: THIS WAS NOT SUPPOSED TO HAPPEN.\n");
                }
            }
            printToConsole("\n");
        }
    }

    // This function prints the final summary after all the guessed are made as well as at
    // the end of the round. The function informs the user on their guesses and their total 
    // score. It also displays whether the string existed in the DSL at the start of the 
    // round and if its word status recorded in the DSL was changed in this round.
    public void printFinalSummary(List<DeterminedString> generatedStrings, List<String> addedStrings, List<String> changedStrings) {
        int score = 0, n = generatedStrings.size();
        for (int i = 0; i < n; i++) {
            DeterminedString ds = generatedStrings.get(i);
            try {
                printToConsole(String.format("%-3s String %-32s   is\t\t%s\n", Integer.toString(i + 1)+".", ds.getString(), (dsl.getDSstatus(ds.getString()) ? "a Word" : "Not a Word")));
                printToConsole(String.format("    You guessed that %-22s   is\t\t%s\n", ds.getString(),(ds.isWord() ? "a Word" : "Not a Word")));
                printToConsole("    Recently added in this round?\t\t       "+ (addedStrings.contains(ds.getString()) ? "YES": "NO") +"\n");
                printToConsole("    Changed status in this round:\t      " + (changedStrings.contains(ds.getString()) ? "Changed from " + (dsl.getDSstatus(ds.getString()) ? "not a word to a word" : "a word to not a word") :"Unchanged word status") +"\n\n");
                score += (dsl.getDSstatus(ds.getString()) == ds.isWord() ? 1 : 0);
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole("\nTHIS SHOULD NOT BE PRINTED LIKE EVER.\n");
            }
        }
        printToConsole("Your score for this round of Guessing Mode is " + score + " out of " + n +".\n");
        String performanceMessage = "";

        double delta = 0.000001, scoreOutOfTotal = (double) score/(double) n;
        if (((scoreOutOfTotal - 0.90) >= delta) || (Math.abs(scoreOutOfTotal - 0.90) <= delta)) {
            performanceMessage = " SUPEEEEEEEEEERRRRRRRRRB!!!!! Really Great Job For Reaching The Peak.";
        } else if ((((scoreOutOfTotal - 0.70) >= delta) || (Math.abs(scoreOutOfTotal - 0.70) <= delta)) && (scoreOutOfTotal < 0.90)) {
            performanceMessage = " excellent. I am sure that with practice, You Will Reach The Top\nSoon!";
        } else if ((((scoreOutOfTotal - 0.50) >= delta) || (Math.abs(scoreOutOfTotal - 0.50) <= delta)) && (scoreOutOfTotal < 0.70)) {
            performanceMessage = " statistically above average. You displayed a Pretty Well-Versed\nVocabulary..";
        } else if ((((scoreOutOfTotal - 0.30) >= delta) || (Math.abs(scoreOutOfTotal - 0.30) <= delta)) && (scoreOutOfTotal < 0.50)) {
            performanceMessage = " pretty.......   bad. You can do even better next time, I Am Sure.";
        } else if ((((scoreOutOfTotal - 0.05) >= delta) || (Math.abs(scoreOutOfTotal - 0.05) <= delta)) && (scoreOutOfTotal < 0.30)) {
            performanceMessage = "...... EH! I am sure that was a fluke. No way you are actually....\nTHIS BAD!!";
        } else {
            performanceMessage = "..............ehem~... WOW!!!! I am IMPRESSED to say the least. You\ndefied all odds to achieve such an...... *softly* unbelievably low ...  score that I am concerned\nif this is how you decide the actions in your Life.";
        }
        printToConsole("Your peformance this round was" + performanceMessage + "\n\n");
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // Implement into the second game mode the feature of being able to change status of any
    // string in the DSL
    public void bootAddMode() {}

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootGroupMode() {}
}