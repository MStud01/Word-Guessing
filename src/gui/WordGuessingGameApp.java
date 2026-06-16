package gui;

import java.util.Timer;
// import java.util.List;
import java.util.Scanner;

import model.DSL;
import model.RSG;
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

public class WordGuessingGameApp {
    // private static final String DSLfile = "";
    private static RSG rsg = RSG.getInstance();
    private static DSL dsl = DSL.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public WordGuessingGameApp() {
        
        start();

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

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void start() {
        System.out.println("Welcome to the Word Guessing Game!\n");
        System.out.println("You can play 2 modes in this game:\n");
        System.out.println("Game Mode 1: Guessing Mode\n");
        System.out.println("Guess whether a number of randomly generated string of English letters is a\nword or not.\n");
        // System.out.println("Game Mode 2: Grouping Mode");
        // System.out.println("Group a number of random English words depending on specific features.");
        System.out.println("Game Mode 2: Adding Mode\n");
        System.out.println("Add new words to the list of Determined Strings (DSL) - the in-game library\nof strings that verifies whether a string is a word or not.\n");
        System.out.println("To play, choose between the above two game modes.\n");
        System.out.println("To quit the game, type 'quit' at any time.\n");

        processInputs();
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void processInputs() {
        String input = "";

        while (!input.equalsIgnoreCase("quit")) {
            System.out.print("What do you wish to do?\n");
            input = scanner.next();

            if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("guessing mode") || input.equalsIgnoreCase("guess")) {
                System.out.println("You have chosen the Guessing Mode.\n");
                bootGuessMode();
            } else if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("adding mode") || input.equalsIgnoreCase("add")) {
                System.out.println("You have chosen the Adding Mode.\n");
                bootAddMode();
            } else if (!input.equalsIgnoreCase("quit")) {
                System.out.println("That is an invalid choice. Please choose between the provided options.\n");
            }
        }

        System.out.println("CHOICE: EXIT\n");
        System.out.println("Thank you for playing the Word Guessing Game!\n");
        System.out.println("Exiting the game now...");
    }

    // TODO: Implement the logic for each game mode in the following functions
    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootGuessMode() {}

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootAddMode() {}
}
