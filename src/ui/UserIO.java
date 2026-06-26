package ui;

import java.util.Scanner;

// TODO: Complete specifications for this class
public enum UserIO {
    INSTANCE; // singleton enum instance so all package classes can access its attr and methods

    public Scanner scanner = new Scanner(System.in);

    // TODO: Might need to change the method name to printToTerminal
    public void printToConsole(String message) {
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
