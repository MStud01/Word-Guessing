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
    GUESSINGGAMEMODE(GuessingGameMode.gameModeNum, GuessingGameMode.gameModeTitle, GuessingGameMode.gameMenuIntroMsg, GuessingGameMode::bootGameMode),
    GROUPINGGAMEMODE(GroupingGameMode.gameModeNum, GroupingGameMode.gameModeTitle, GroupingGameMode.gameMenuIntroMsg, GroupingGameMode::bootGameMode),
    ADDINGGAMEMODE(AddingGameMode.gameModeNum, AddingGameMode.gameModeTitle, AddingGameMode.gameMenuIntroMsg, AddingGameMode::bootGameMode),
    STREAKGAMEMODE(StreakGameMode.gameModeNum, StreakGameMode.gameModeTitle, StreakGameMode.gameMenuIntroMsg, StreakGameMode::bootGameMode);

    private int gameModeNum;
    private String gameModeTitle;
    private String gameMenuIntroMsg;
    private Runnable gameModeBooter;

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
