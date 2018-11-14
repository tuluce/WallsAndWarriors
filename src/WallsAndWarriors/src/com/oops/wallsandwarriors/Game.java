package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.screens.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Game {
    
    private static Game instance;
    private static Stage stage;
    
    public final ChallengeManager challengeManager;
    public final GridManager gridManager;
    public final ScreenManager screenManager;
    public final SolutionManager solutionManager;
    public final StorageManager storageManager;
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    private Game() {
        challengeManager = new ChallengeManager();
        gridManager = new GridManager();
        screenManager = new ScreenManager();
        solutionManager = new SolutionManager();
        storageManager = new StorageManager();

        new File("C:\\W&W\\CampaignChallenges").mkdirs();
        new File("C:\\W&W\\CustomChallenges").mkdirs();
    }
    
    public void initGame(Stage stage) {
        Game.stage = stage;
        setScreen(screenManager.mainMenu);
        stage.setTitle("Walls & Warriors");
        stage.setResizable(false);
        stage.show();
    }
    
    public void setScreen(Screen screen) {
        stage.setScene(screen.getScene());
        stage.sizeToScene();
    }
}
