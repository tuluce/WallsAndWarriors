package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.scene.Group;
import javafx.scene.Scene;

public class CampaignChallengesScreen extends ParentScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        renderBackgroundCanvas(root, "resources/images/background2.png", "Campaign Challenges");
        renderButtons(root);
        
        return scene;
    }
    
    private void renderButtons(Group root) {
        addTransactionButton(root, "Back", 300, 300, Game.MAIN_MENU);
    }
    
}
