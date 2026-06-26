package ui;

import model.DSL;

import ui.gamemodes.GameModes;

// TODO: Add a logging system to log user inputs and game outcomes
// TODO: Add a saving system to save the user's progress and the list of Determined Strings (DSL) to a file

// An application that utilizes the Scanner class to create a command-line
// interface for the Word Guessing Game, allowing the user to  
//                      1) determine whether a set of randomly generated strings is an actual word or not, and
//                      2) add new words to DSL (the list of Determined Strings)

// TODO: Change the name of WordGuessingGame to WordGame or something similar
public class WordGuessingGame {
    // private static final String DSLfile = "";
    private static DSL dsl = DSL.getInstance();
    private static int gameModeCount = GameModes.values().length;  

    // TODO: implement a list of gameModes so that it can be easily iterated over to access
    // their fields and set currGameMode

    /** Looked into not needing to hardcode the list of gamemodes and found some interesting
     * trove of user-defined libraries that improved on the Reflection API's capabilitie:
     * https://stackoverflow.com/questions/205573/at-runtime-find-all-classes-in-a-java-application-that-extend-a-base-class
     * 
     * One library of particular interest is the ClassGraph API worked on by Luke Hutchinson:
     * https://github.com/classgraph/classgraph which seems useful for deep hierarchical class
     * structures as it is capable of recursively find subclasses of a class.
     */

    private static GameModes currGameMode;

    public WordGuessingGame() {
        // TODO: Change the delimiter if necessary
        // UserIO.INSTANCE.scanner.useDelimiter("");
        // loadDSL(dsl);
        UserSettings.initializeSettings();
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

    // Boots up the start menu where the user is introduced to the game and the game modes
    // and is prompted in the subsequent function call
    public void start() {
        UserIO.INSTANCE.printToConsole("Welcome to the Word Guessing Game!\n\n\n");
        UserIO.INSTANCE.printToConsole("You can play " + gameModeCount + " modes in this game:\n\n");
        for (int i = 1; i <= gameModeCount; i++) {
            GameModes gm = GameModes.values()[i - 1];
            UserIO.INSTANCE.printToConsole("Game Mode " + i + ": " + gm.getTitle() + "\n" + gm.getGameMenuIntroMsg() + "\n\n");
        }
        UserIO.INSTANCE.printToConsole("To play, choose between the above game modes.\n");
        processInputs();
    }

    // The user is prompted to choose among the previously presented options in start() and
    // the appropriate message is printed and a game mode is booted up through a function call
    // or the program exits according to the user's input, unless the inputs do not match any
    // of the presented options, upon which the user is prompted until the right input
    // has been entered by the user.
    public void processInputs() {
        String input = "message";

        while (!input.equalsIgnoreCase("quit")) {
            UserIO.INSTANCE.printToConsole("What game mode do you wish to play?\n");
            input = UserIO.INSTANCE.scanner.next();
            UserIO.INSTANCE.printToConsole("\n");

            if (input.matches("^[1-"+ Integer.toString(gameModeCount) +"]$")) {
                currGameMode = GameModes.values()[Integer.parseInt(input) - 1];
                UserIO.INSTANCE.printToConsole("You have chosen the " + currGameMode.getTitle() + ".\n");
                currGameMode.getGameModeBooter().run();
            } else {
                for (GameModes gm : GameModes.values()) {
                    // TODO: Fix the error when the entire game mode title is entered by the user 
                    if (input.equalsIgnoreCase(gm.getTitle().substring(0, gm.getTitle().indexOf(" ")))) {
                        UserIO.INSTANCE.printToConsole("You have chosen the " + gm.getTitle() + ".\n");
                        currGameMode = gm;
                        currGameMode.getGameModeBooter().run();
                        break;
                    }
                }
                if ((!input.equalsIgnoreCase("quit")) && (currGameMode == null)) {
                    UserIO.INSTANCE.printToConsole("That is an invalid choice. Please choose between the provided options.\n");
                }
            }
            currGameMode = null;
        }

        UserIO.INSTANCE.printToConsole("CHOICE: QUIT\n\n");
        UserIO.INSTANCE.printToConsole("Thank you for playing the Word Guessing Game!\n");
        UserIO.INSTANCE.printToConsole("Exiting the game now...");
    }
}