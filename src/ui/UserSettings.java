package ui;

// A class that stores user-defined settings as fields with methods prompting the user to set each
// of these fields. 
// Currently, the user is only able to set the game speed field which determines how fast the game
// prints each line to the terminal.
// In the future versions of this app, fields for textSize and textFont will be implemented.
public class UserSettings {
    static int gameSpeed = 7; // default value is 7, ranges from 1 to 10
    // each decrement raises the delay between each action in the game, i.e. makes the game slower

    // TODO: Look into if other settings like text font or size can be init
    static void initializeSettings() {
        setGameSpeed();
    }

    static void setGameSpeed() {
        String response = "hidden";

        while (response.toLowerCase().charAt(0) != 's') {// user is meant to type 'start'
            UserIO.INSTANCE.printToTerminal("Set the game speed from a scale from 1 to 10.\n");
            UserIO.INSTANCE.printToTerminal("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.\n");
            response = UserIO.INSTANCE.scanner.nextLine();
            UserIO.INSTANCE.printToTerminal("\n");
            // user is meant to type a number from 1 to 10
            if (response.matches("^(?:[1-9]|10)$")) {
                gameSpeed = Integer.parseInt(response);
            } else if (response.equalsIgnoreCase("ULTRA")) {
                gameSpeed = 100;
            }
        }
    }

}
