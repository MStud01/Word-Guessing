package ui.gamemodes;

// TODO: Complete specifications for this class
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
