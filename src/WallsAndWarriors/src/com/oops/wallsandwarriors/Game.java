package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.screens.CampaignChallengesScreen;
import com.oops.wallsandwarriors.screens.ChallengeEditorScreen;
import com.oops.wallsandwarriors.screens.Screen;
import com.oops.wallsandwarriors.screens.CreditsScreen;
import com.oops.wallsandwarriors.screens.CustomChallengesScreen;
import com.oops.wallsandwarriors.screens.HowToPlayScreen;
import com.oops.wallsandwarriors.screens.MainMenuScreen;
import com.oops.wallsandwarriors.screens.SettingsScreen;
import javafx.application.Application;
import javafx.stage.Stage;
 
public class Game extends Application {

    public static final Screen MAIN_MENU = new MainMenuScreen();
    public static final Screen CAMPAIGN_CHALLENGES = new CampaignChallengesScreen();
    public static final Screen CUSTOM_CHALLENGES = new CustomChallengesScreen();
    public static final Screen CHALLENGE_EDITOR = new ChallengeEditorScreen();
    public static final Screen SETTINGS = new SettingsScreen();
    public static final Screen HOW_TO_PLAY = new HowToPlayScreen();
    public static final Screen CREDITS = new CreditsScreen();
    
    private static Stage stage;
    private static Game instance;
    
    public static Game getInstance() {
        return instance;
    }
    
    public Game() {
        instance = this;
    }
    
    @Override
    public void start(Stage stage) {
        Game.stage = stage;
        setScreen(MAIN_MENU);
        stage.setTitle("Walls & Warriors");
        stage.setResizable(false);
        stage.show();
    }
    
    public void setScreen(Screen screen) {
        stage.setScene(screen.getScene());
        stage.sizeToScene();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
