package gui;

import java.util.Timer;
import java.util.ArrayList;
import java.util.List;
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

    // TODO: Refactor the following function so that it is more readable and makes calls to multiple functions for readability
    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootGuessMode() {
        // TODO: Consider changing the generic type from String to DeterminedString so that it is easier for the strings to be added in case they are not.
        List<String> generatedStrings = new ArrayList<String>();
        
        generateAndAddStrings(generatedStrings);

        System.out.println("Here is your set of generated strings:\n");
        int n = generatedStrings.size();
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "." +  generatedStrings.get(i) + ((i % 5 == 4) ? "\n" : "\t"));
        }

        System.out.println("Now, you can guess whether each string is a word or not.\n");

        while (n > 0) {
            System.out.println("Choose a string from the generated strings above and type it in to guess.\n");
            String selectedString = scanner.next();
            // TODO: Implement this line of code to find the selectedString in the generatedStrings list and verify that it exists
            // generatedStrings.find(selectedString);
            System.out.println("Do you think this string is a word? Type 'y' if you do think so, else type 'n'.\n");
            String guess = scanner.next();
            
            boolean guessIsWord;
                        
            if ((guess.toLowerCase().charAt(0) == 'y') || (guess.toLowerCase().charAt(0) == 'w')) {
                System.out.println("You have guessed that the string is a word.\n");
                guessIsWord = true;
            } else if (guess.toLowerCase().charAt(0) == 'n') {
                System.out.println("You have guessed that the string is not a word.\n");
                guessIsWord = false;
            } else {
                System.out.println("That is an invalid choice. Please choose between the provided options.\n");
                continue;
            }

            boolean confirmation = confirmGuess(selectedString, guessIsWord);
            // TODO: confirmGuess should also verify if the selectedString exists in the DSL
            // TODO: and implement the logic for when it does not exist, and ask the user whether it should be added to the DSL or not

            if (!confirmation) {
                System.out.println("Your guess was correct!!\n");
            } else {
                System.out.println("Unfortunately, uour guess was incorrect...\n");
                System.out.println("The string you guessed was actually " + (guessIsWord ? "not a word" : "a word") + ".\n");
                System.out.println("Would you like to change the status of this string to " + (guessIsWord ? " " : "not ") + "be a word?\n");
                String changeStatus = scanner.next();

                // TODO: Revise the following generated if block
                if ((changeStatus.toLowerCase().charAt(0) == 'y') || (changeStatus.toLowerCase().charAt(0) == 'c')) {
                    System.out.println("You have chosen to change the status of this string.\n");
                    dsl.addDS(selectedString, guessIsWord);
                } else if (changeStatus.toLowerCase().charAt(0) == 'n') {
                    System.out.println("You have chosen to not change the status of this string.\n");
                } else {
                    System.out.println("That is an invalid choice. The status of this string will not be changed.\n");
                }

                System.out.println("The status of the string " + selectedString + " can be changed at a later time at the end of the game or in the second game mode.\n");
                // TODO: Implement the functionality stated in the above line into the first and second game modes
            }

            n--;
        }
        
        printSummary(generatedStrings);
        // TODO: Summary could possibly also indicate if the string existed in the DSL or not and if its recorded status was changed in the DSL
        
        promptForNextChange(generatedStrings);
        // TODO: This function could be implemented instead to allow the user to change the status of the strings in the DSL
    }

    public void generateAndAddStrings(List<String> generatedStrings) {
        System.out.println("How large do you should he set of strings be?\n");
        int setSize = scanner.nextInt();

        for (int i = 0; i < setSize; i++) {
            System.out.println("How long do you wish the string to be?\n");
            String generatedString = rsg.generateString(scanner.nextInt());
            generatedStrings.add(generatedString);
        }
    }

    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void bootAddMode() {}
}
