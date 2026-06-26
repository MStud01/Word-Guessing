package ui.gamemodes;

// An abstract class that represents a game mode with a number, title and an introduction message
// that explains the rules of the game
// It has specific methods that are implemented in each of the game modes

// TODO: Fix the inheritance for this class
public abstract class GameMode {
    protected static int gameModeNum;
    protected static String gameModeTitle;
    protected static String gameMenuIntroMsg;

    static void bootGameMode() { }

    protected void printSummary() { }
    
    protected void printSummaryRoundInProgress() { }

    protected void printSummaryDebug() { }
}
