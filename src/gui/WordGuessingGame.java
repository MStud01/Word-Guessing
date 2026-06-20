package gui;

// import java.util.Timer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import model.DSL;
import model.RSG;
import model.exceptions.DeterminedStringNotFoundException;
import model.DeterminedString;

// TODO: Add a timer to allow the user to read each sentence before another one is printed.
// TODO: Add a slider to adjust game speed.
// TODO: Print each character of each sentence one by one for cool effects.

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
        System.out.println("");
    }

    public void setGameSpeed() {
        printToConsole("Set the game speed from a scale from 1 to 10.");
        printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.");
            // try {
            //     printToConsole("Here is a sample message ");
            // } catch (InterruptedException ie) {
            // }
        String response = scanner.next();
        System.out.println("");
        if (response.matches("^(?:[1-9]|10)$")) {
            gameSpeed = Integer.parseInt(response);
        } else if (response.equalsIgnoreCase("ULTRA")) {
                gameSpeed = 100;
        }
        // if (response.matches("^(?:[1-9]\\d?|100)$")) {  // for 1 to 100
            
        // }
        while (response.toLowerCase().charAt(0) != 's') {
            printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.");
            // try {
            //     printToConsole("Here is a sample message ");
            // } catch (InterruptedException ie) {
            // }
            response = scanner.next();
            System.out.println("");
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
        printToConsole("Welcome to the Word Guessing Game!\n");
        printToConsole("You can play 2 modes in this game:\n");
        printToConsole("Game Mode 1: Guessing Mode\nGuess whether a number of randomly generated string of English letters is a word or not.\n");
        // printToConsole("Game Mode 2: Grouping Mode\nGroup a number of random English words depending on specific features.\n");
        printToConsole("Game Mode 2: Adding Mode\nAdd new words to the list of Determined Strings (DSL) - the in-game library\nof strings that verifies whether a string is a word or not.\n\n");
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
            printToConsole("");

            if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("guessing mode") || input.equalsIgnoreCase("guess")) {
                printToConsole("You have chosen the Guessing Mode.\n");
                bootGuessMode();
            } else if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("adding mode") || input.equalsIgnoreCase("add")) {
                printToConsole("You have chosen the Adding Mode.\n");
                bootAddMode();
            } else if (!input.equalsIgnoreCase("quit")) {
                printToConsole("That is an invalid choice. Please choose between the provided options.\n");
            }
        }

        printToConsole("CHOICE: QUIT\n");
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
        // TODO: Consider changing the generic type from String to DeterminedString so that it is easier for the strings to be added in case they are not.
        // TODO: Also consider changing the container type to HashMap to pair DeterminedStrings with their guessed status
        List<DeterminedString> generatedStrings = new ArrayList<DeterminedString>();
        
        generateStrings(generatedStrings);

        // TODO: Possibly implment logic for new rounds
        printToConsole("Here is your set of generated strings:\n");
        int n = generatedStrings.size();
        for (int i = 0; i < n; i++) {
            printToConsole((i + 1) + "." +  generatedStrings.get(i).getString() + ((i % 5 == 4) ? "\n" : "\t"));
        }

        printToConsole("\nNow, you can guess whether each string is a word or not.\n");
        while (n > 0) {
            // TODO: Print the status of the generated strings that were guessed
            printToConsole("Choose a string from the generated strings above and type it in to guess.");
            printToConsole("Or you can type \"skip\" if you think the remainder of the strings cannot possibly be English words.\n");
            String response = scanner.next();
            if (response.equalsIgnoreCase("skip")) {
                // TODO: Add functionality for setting all unguessed strings to be guessed as not words
                break;
            }
            // TODO: look into below line for case-sensitivity checks 
            //  String selectedString = (char) (response.charAt(0) - ('A' - 'a')) + response.substring(1, response.length());
            String selectedString = response;
            if (generatedStrings.indexOf(new DeterminedString(selectedString, false)) == -1) {
                printToConsole("That was not one of the randomly generated strings. Try typing it again.\n");
                continue;
            } else if (dsl.getDS(selectedString) == null) {
                printToConsole("The string does not exist in the library. Try adding it first.\n");
                // TODO: Implement behaviour for when the string does exist upon it beig chosen
                promptforAddDS(selectedString, false);
                printToConsole("CHANGE FUNCTIONALITY HERE");
            }
            printToConsole("Do you think this string is a word? Type \"Yes\" if you do think so, else type \"No\".\n");
            String guess = scanner.next();
            
            boolean guessIsWord;
                        
            if ((guess.toLowerCase().charAt(0) == 'y') || (guess.toLowerCase().charAt(0) == 'w')) {
                printToConsole("You have guessed that the string is a word.\n");
                guessIsWord = true;
            } else if (guess.toLowerCase().charAt(0) == 'n') {
                printToConsole("You have guessed that the string is not a word.\n");
                guessIsWord = false;
            } else {
                printToConsole("That is an invalid choice. Please choose between the provided options.\n");
                continue;
            }

            try {
                if (dsl.getDSstatus(selectedString) == guessIsWord) {
                    printToConsole("Your guess was correct!!\n");
                } else {
                    printToConsole("Unfortunately, uour guess was incorrect...\n");
                    printToConsole("The string you guessed was actually " + (guessIsWord ? "not a word" : "a word") + ".\n");
                    promptforChangeStatus(selectedString, guessIsWord);
                }
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole(dsnfe.getMessage() + " Try adding this Determined String first.\n");
                promptforAddDS(selectedString, guessIsWord);
                // System.out.println("Exception: " + dsnfe.getMessage());
                // System.out.println("THIS LINE SHOULD NOT HAVE BEEN REACHED");
                // quit();
            } finally {
                n--;
            }
        }
        
        // TODO: Revise the code from this point onwards.
        // printSummary(generatedStrings);
        
        printToConsole("Do you want to change the status of any of the above strings?\n");
        String response = scanner.next();

        while ((response.toLowerCase().charAt(0) == 'y') || (response.toLowerCase().charAt(0) == 'c')) {
            printToConsole("Please type in the string that you wish to change the status of.\n");
            String selectedString = scanner.next();
            try {
                boolean status = dsl.getDSstatus(selectedString);
                promptforChangeStatus(selectedString, !status);
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole(dsnfe.getMessage() + " Try adding this Determined String first.\n");
                printToConsole("Would you want this string to be considered a word?\n");
                boolean status = scanner.next().toLowerCase().charAt(0) == 'y';
                // TODO: Modify the passing of status
                promptforAddDS(selectedString, status);
            } finally {
                // TODO: Add an early exit option here after I'm done changing the status of some strings 
                // printToConsole("Is that all? If you do not wish to make any more changes, type \"skip\".\n");
                // String response = scanner.next();
                // if 
            }

            // TODO: print updated summary after changing status of strings
        }
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void generateStrings(List<DeterminedString> generatedStrings) {
        printToConsole("How large do you should he set of strings be?\n");
        int setSize = scanner.nextInt();

        for (int i = 0; i < setSize; i++) {
            printToConsole("How long do you wish the string to be?\n");
            String generatedString = rsg.generateString(scanner.nextInt());
            generatedStrings.add(new DeterminedString(generatedString, false));
        }
        // TODO: Add the strings to the DSL after generating them
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void promptforChangeStatus(String selectedString, boolean newStatus) {
        printToConsole("Would you like to change the status of the string " + selectedString + " to " + (newStatus ? "" : "not ") + "be a word?\n");
        String prompt = scanner.next();

        // TODO: Revise the following generated if block
        if ((prompt.toLowerCase().charAt(0) == 'y')) {
            printToConsole("You have chosen to change the status of this string.\n");
            dsl.getDS(selectedString).setStatus(newStatus);
            // generatedStrings[selectedString] = true;
            // TODO: implement above line to confirm that the statu so the ds has been changed
        } else if (prompt.toLowerCase().charAt(0) == 'n') {
            printToConsole("You have chosen to not change the status of this string.\n");
        } else {
            printToConsole("That is an invalid choice. The status of this string will not be changed.\n");
        }

        printToConsole("The status of the string " + selectedString + " can be changed at the end of the game or in the second game mode.\n");
        // TODO: Implement the functionality stated in the above line into the first and second game modes
    }

    // TODO: This function might need to be removed if I decide to add generated strings to DSL
    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void promptforAddDS(String selectedString, boolean guessIsWord) {
        printToConsole("Would you like to add this string to the DSL with the status of " + (guessIsWord ? "being a word" : "not being a word") + "?\n");
        String addToDSL = scanner.next();

        if ((addToDSL.toLowerCase().charAt(0) == 'y')) {
            printToConsole("You have chosen to add this string to the DSL.\n");
            dsl.addDS(selectedString, guessIsWord);
        } else {
            if (addToDSL.toLowerCase().charAt(0) == 'n') {
                printToConsole("You have chosen to not add this string to the DSL.\n");
            } else {
                printToConsole("That is an invalid choice. The string will not be added to the DSL.\n");
            }
            printToConsole("The string " + selectedString + " can be added to the DSL at a later time at the end of the game or in the second game mode.\n");
        }
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // Summary could possibly also indicate if the string existed in the DSL or not and if its recorded status was changed in the DSL
    public void printSummary(List<String> generatedStrings) {
        // TODO Complete this function
        for (int i = 0; i < generatedStrings.size(); i++) {
            String string = generatedStrings.get(i);
            try {
                printToConsole((i + 1) + ". " + string + "\tis\t" + (dsl.getDSstatus(string) ? "a Word" : "Not a Word"));
                printToConsole("Changed Status: " + "DEFAULT VALUE");
                printToConsole("Recently added: "+ "DEFAULT VALUE\n");
            } catch (DeterminedStringNotFoundException dsnfe) {
                printToConsole("THIS SHOULD NOT BE PRINTED LIKE EVER.");
                dsnfe.printStackTrace();
            }
        }
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootAddMode() {}
}