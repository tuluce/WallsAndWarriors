package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

public class MainMenuScreen extends GeneralScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background.png", "Walls & Warriors");
        addButtons(root);
        
        return scene;
    }

    private void addButtons(Group root) {
        addTransactionButton(root, "Campaign Challenges", 300, 300, Game.getInstance().screenManager.campaignChallenges);
        addTransactionButton(root, "Custom Challenges", 300, 350, Game.getInstance().screenManager.customChallenges);
        addTransactionButton(root, "Challenge Editor", 300, 400, Game.getInstance().screenManager.challengeEditor);
        addTransactionButton(root, "Settings", 300, 450, Game.getInstance().screenManager.settings);
        addTransactionButton(root, "How to Play", 300, 500, Game.getInstance().screenManager.howToPlay);
        addTransactionButton(root, "Credits", 300, 550, Game.getInstance().screenManager.credits);

        addButton(root, "Challenge Editor", 300, 400, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Screen editorScreen = Game.getInstance().screenManager.challengeEditor;
                Game.getInstance().challengeManager.initChallengeData();
                Game.getInstance().setScreen(editorScreen);
            }
        });
    }
    
}
