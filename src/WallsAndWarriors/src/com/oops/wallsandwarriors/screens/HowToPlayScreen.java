package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.scene.Group;
import javafx.scene.Scene;

public class HowToPlayScreen extends ParentScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "How to Play");
        addTransactionButton(root, "Back", 300, 300, Game.MAIN_MENU);
        
        return scene;
    }
    
}
