package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.screens.Screen;
import javafx.stage.Stage;


public class Game {
    
    private static Game instance;
    private static Stage stage;
    
    public final ChallengeManager challengeManager;
    public final ChallengeManager challengeSolutionManager;
    public final GridManager gridManager;
    public final ScreenManager screenManager;
    public final SolutionManager solutionManager;
    public final StorageManager storageManager;
    public final SettingsManager settingsManager;
    public final SoundManager soundManager;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private Game() {
        challengeManager = new ChallengeManager();
        challengeSolutionManager = new ChallengeManager();
        gridManager = new GridManager();
        screenManager = new ScreenManager();
        solutionManager = new SolutionManager();
        storageManager = new StorageManager();
        settingsManager = new SettingsManager();
        soundManager = new SoundManager();
    }
    
    public void initGame(Stage stage) {
        Game.stage = stage;
        setScreen(screenManager.mainMenu);
        stage.setTitle("Walls & Warriors");
        stage.setResizable(false);
        stage.show();
        settingsManager.setVolume(Game.getInstance().storageManager.readSoundSetting());
        settingsManager.setMusicVolume(Game.getInstance().storageManager.readMusicSetting());
        soundManager.startPlayMusic();
    }
    
    public void setScreen(Screen screen) {
        stage.setScene(screen.getScene());
        stage.sizeToScene();
    }
    
}
