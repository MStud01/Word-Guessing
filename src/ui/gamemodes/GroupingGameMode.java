package ui.gamemodes;

/** In this game mode, the user is given a set of randomly selected words from the DSL and the 
 user is asked to determine whether certain words are related in any of the word's features.  
 The higher the number of words in each group, the higher the score the user gets.
 The features of the words are then stored into the DSL if any groups with these identified 
 features (containing more than 1 word) have been created, so they can be referred to 
 in future rounds of this game mode when the user specifies these features in future rounds.
 TODO: Implement the above logic
 */
// TODO: Implement methods for this class
public class GroupingGameMode extends GameMode {
    static int gameModeNum = 2;
    static String gameModeTitle = "Grouping Mode";
    static String gameMenuIntroMsg = "Group a number of random English words depending on specific features.";

    
    // TODO: Complete the specifications for this function
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    static void bootGameMode() { }

    @Override
    protected void printSummary() { }
    
    @Override
    protected void printSummaryRoundInProgress() { }

    @Override
    protected void printSummaryDebug() { }
}
