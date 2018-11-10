package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.scene.Group;
import javafx.scene.Scene;

public class CreditsScreen extends GeneralScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Credits");
        addTransactionButton(root, "Back", 300, 300, Game.getInstance().screenManager.mainMenu);
        
        return scene;
    }
    
}
