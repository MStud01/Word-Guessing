package ui.gamemodes;

/** An enum that stores the static fields of all classes that inherit the GameMode class to 
 prevent the need to initialize any of these classes. These can be accessed in the WordGameApp
 class as an array to iterate and match with user input.
 Currently, the enum is storing fields of:
1) GuessingGameMode class
2) GroupingGameMode class
3) AddingGameMode class
4) StreakGameMode class
 and will be updated as more game modes are added.
*/
public enum GameModes {    
    /** NOTE TO MYSELF AND OTHERS ABOUT THE CHANGE
        The reason why I stuck with passing a static method of a GameMode class is that I 
        had found an opportunity to utilize this passing of a method that I had learnt a year ago 
        but had not found the opportunity to do so.
        Now I realized that I only ended up making the class dependencies a lot more difficult to
        analyze on a glance by doing so. As such, I am changing it so I pass in a constructor 
        of the corresponding GameMode class which is initialized at runtime in the WordGameApp class
        where the corresponding GameMode object's bootGameMode() method is run thus making the 
        dependency of the WordGameApp class on all of the GameMode class clear.
        This change also allows me to properly utilize OOP principles I learnt a year ago of
        inheritance which was not properly realized with my implementations for the GameMode class
        and its subclasses.
        And I have realized that I need to keep a proper record of the changes I make along with the 
        reasons behind the changes or implementations in my code as the commit messages do not
        provide the ability/make it clear in listing these details.
        // TODO: Look into creating a file that allows me to post what updates I have made to the code
        // in detail and why I have done so. 
    */
    GUESSINGGAMEMODE(GuessingGameMode.gameModeNum, GuessingGameMode.gameModeTitle, GuessingGameMode.gameMenuIntroMsg, GuessingGameMode::bootGameMode),
    GROUPINGGAMEMODE(GroupingGameMode.gameModeNum, GroupingGameMode.gameModeTitle, GroupingGameMode.gameMenuIntroMsg, GroupingGameMode::bootGameMode),
    ADDINGGAMEMODE(AddingGameMode.gameModeNum, AddingGameMode.gameModeTitle, AddingGameMode.gameMenuIntroMsg, AddingGameMode::bootGameMode),
    STREAKGAMEMODE(StreakGameMode.gameModeNum, StreakGameMode.gameModeTitle, StreakGameMode.gameMenuIntroMsg, StreakGameMode::bootGameMode);

    private int gameModeNum;
    private String gameModeTitle;
    private String gameMenuIntroMsg;
    private Runnable gameModeBooter;
    // TODO: Replace the above field with the constructor for the corresponding GameMode class

    private GameModes(int gameModeNum, String gameModeTitle, String gameMenuIntroMsg, Runnable gameModeBooter) {
        this.gameModeNum = gameModeNum;
        this.gameModeTitle = gameModeTitle;
        this.gameMenuIntroMsg = gameMenuIntroMsg;
        this.gameModeBooter = gameModeBooter;
    }
    
    public int getGameModeNum() {
        return this.gameModeNum;
    } 

    public String getTitle() {
        return this.gameModeTitle;
    } 

    public String getGameMenuIntroMsg() {
        return this.gameMenuIntroMsg;
    } 

    public Runnable getGameModeBooter() {
        return this.gameModeBooter;
    }
}
