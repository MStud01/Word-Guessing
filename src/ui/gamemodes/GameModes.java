package ui.gamemodes;

import java.util.function.Supplier;

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
    GUESSINGGAMEMODE(GuessingGameMode.gameModeNum, GuessingGameMode.gameModeTitle, GuessingGameMode.gameMenuIntroMsg, GuessingGameMode::new),
    GROUPINGGAMEMODE(GroupingGameMode.gameModeNum, GroupingGameMode.gameModeTitle, GroupingGameMode.gameMenuIntroMsg, GroupingGameMode::new),
    ADDINGGAMEMODE(AddingGameMode.gameModeNum, AddingGameMode.gameModeTitle, AddingGameMode.gameMenuIntroMsg, AddingGameMode::new),
    STREAKGAMEMODE(StreakGameMode.gameModeNum, StreakGameMode.gameModeTitle, StreakGameMode.gameMenuIntroMsg, StreakGameMode::new);

    private int gameModeNum;
    private String gameModeTitle;
    private String gameMenuIntroMsg;
    private Supplier<GameMode> gameModeConstructor;

    private GameModes(int gameModeNum, String gameModeTitle, String gameMenuIntroMsg, Supplier<GameMode> gameModeConstructor) {
        this.gameModeNum = gameModeNum;
        this.gameModeTitle = gameModeTitle;
        this.gameMenuIntroMsg = gameMenuIntroMsg;
        this.gameModeConstructor = gameModeConstructor;
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

    public Supplier<GameMode> getConstructor() {
        return this.gameModeConstructor;
    }
}
