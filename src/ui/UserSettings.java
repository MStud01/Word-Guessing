package ui;

// TODO: Complete specifications for this class
public class UserSettings {
    static int gameSpeed = 7; // default value is 7, each decrement raises the delay between each action

    static void initializeSettings() {
        setGameSpeed();
    }

    static void setGameSpeed() {
        UserIO.INSTANCE.printToConsole("Set the game speed from a scale from 1 to 10.\n");
        UserIO.INSTANCE.printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.\n");
        String response = UserIO.INSTANCE.scanner.next();
        UserIO.INSTANCE.printToConsole("\n");
        if (response.matches("^(?:[1-9]|10)$")) {
            gameSpeed = Integer.parseInt(response);
        } else if (response.equalsIgnoreCase("ULTRA")) {
            gameSpeed = 100;
        }
        while (response.toLowerCase().charAt(0) != 's') {
            UserIO.INSTANCE.printToConsole("Here is a sample message that you can use to judge the game speed and change it to your liking.\nThe higher the number you choose, the faster the game's speed is. Type \"start\" if you are satisfied\nwith the game's speed.\n");
            response = UserIO.INSTANCE.scanner.next();
            UserIO.INSTANCE.printToConsole("\n");
            if (response.matches("^(?:[1-9]|10)$")) {
                gameSpeed = Integer.parseInt(response);
            } else if (response.equalsIgnoreCase("ULTRA")) {
                gameSpeed = 100;
            }
        }
    }

}
