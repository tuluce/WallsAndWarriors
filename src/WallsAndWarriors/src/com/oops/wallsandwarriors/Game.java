package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.screens.Screen;
import javafx.stage.Stage;

/**
 * Represents the currently running game instance
 * @author Emin Bahadir Tuluce
 */
public class Game {
    
    private static Game instance;
    private static Stage stage;
    
    public final ChallengeManager challengeManager;
    public final HintManager hintManager;
    public final GridManager gridManager;
    public final ScreenManager screenManager;
    public final SolutionManager solutionManager;
    public final StorageManager storageManager;
    public final SettingsManager settingsManager;
    public final SoundManager soundManager;

    /**
     * Gives the singleton instance of the game
     * @return the instance of the game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private Game() {
        challengeManager = new ChallengeManager();
        hintManager = new HintManager();
        gridManager = new GridManager();
        screenManager = new ScreenManager();
        solutionManager = new SolutionManager();
        storageManager = new StorageManager();
        settingsManager = new SettingsManager();
        soundManager = new SoundManager();
    }
    
    /**
     * Initializes the game
     * @param stage the JavaFX stage for the game to run on
     */
    public void initGame(Stage stage) {
        Game.stage = stage;
        setScreen(screenManager.mainMenu);
        stage.setTitle("Walls & Warriors");
        stage.setResizable(false);
        stage.show();
        settingsManager.readSettings();
        soundManager.startPlayMusic();
    }
    
    /**
     * Changes the screen currently being displayed
     * @param screen the new screen to display
     */
    public void setScreen(Screen screen) {
        stage.setScene(screen.getScene());
        stage.sizeToScene();
    }
    
}
