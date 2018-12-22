package com.oops.wallsandwarriors;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A launcher class for the game instance
 * @author Emin Bahadir Tuluce
 */
public class GameLauncher extends Application {
    
    /**
     * Initializes the game instance.
     * @param stage the stage that the game will run on
     */
    @Override
    public void start(Stage stage) {
        Game.getInstance().initGame(stage);
    }
    
    /**
     * Launches the JavaFX application
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
