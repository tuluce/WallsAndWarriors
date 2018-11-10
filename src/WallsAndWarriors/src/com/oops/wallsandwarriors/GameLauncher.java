package com.oops.wallsandwarriors;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application {
    
    @Override
    public void start(Stage stage) {
        Game.getInstance().initGame(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
