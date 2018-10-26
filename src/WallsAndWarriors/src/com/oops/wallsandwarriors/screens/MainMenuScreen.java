package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.scene.Group;
import javafx.scene.Scene;

public class MainMenuScreen extends ParentScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        renderBackgroundCanvas(root, "resources/images/background.png", "Walls & Warriors");
        renderButtons(root);
        
        return scene;
    }

    private void renderButtons(Group root) {
        addTransactionButton(root, "Campaign Challenges", 300, 300, Game.CAMPAIGN_CHALLENGES);
        addTransactionButton(root, "Custom Challenges", 300, 350, Game.CUSTOM_CHALLENGES);
        addTransactionButton(root, "Challenge Editor", 300, 400, Game.CHALLENGE_EDITOR);
        addTransactionButton(root, "Settings", 300, 450, Game.SETTINGS);
        addTransactionButton(root, "How to Play", 300, 500, Game.HOW_TO_PLAY);
        addTransactionButton(root, "Credits", 300, 550, Game.CREDITS);
    }
    
}
