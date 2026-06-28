package ui;

import java.util.Scanner;

// A singleton class that creates an interactive terminal for the user to play the game.
// Each class in ui accesses the singleton instance to print outputs and accept user inputs.
// Currently, this class only allows text outputs and will output images in future versions.
public enum UserIO {
    INSTANCE; // singleton enum instance so all package classes can access its attr and methods

    public Scanner scanner = new Scanner(System.in);

    // A custom print method that utilizes Thread manipulation to print the given string 
    // character by character with a slight delay between each character being printed.
    public void printToTerminal(String message) {
        for (char c: message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(50/UserSettings.gameSpeed);
                // gameSpeed is set by user when the game is run
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        // System.out.println(""); can be added if no formatting is used
    }

}
