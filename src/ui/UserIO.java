package ui;

import java.util.Scanner;

// TODO: Complete specifications for this class
public enum UserIO {
    INSTANCE;

    public Scanner scanner = new Scanner(System.in);

    public void printToConsole(String message) {
        for (char c: message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(50/UserSettings.gameSpeed); // gameSpeed is set by user when main() is run
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
            // TImer timer = new Timer(0, null)
        }
        // System.out.println("");
    }

}
