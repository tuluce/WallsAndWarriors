package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_HEIGHT;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_WIDTH;
import com.oops.wallsandwarriors.util.FileUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenuScreen extends GeneralScreen {
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        GraphicsContext graphics = addBackgroundCanvas(root);
        drawUi(graphics);
        addButtons(root);
        
        return scene;
    }

    private void addButtons(Group root) {
        addTransactionButton(root, "Campaign Challenges", 300, 250, Game.getInstance().screenManager.campaignChallenges);
        addTransactionButton(root, "Custom Challenges", 300, 300, Game.getInstance().screenManager.customChallenges);
        addTransactionButton(root, "Settings", 300, 400, Game.getInstance().screenManager.settings);
        addTransactionButton(root, "How to Play", 300, 450, Game.getInstance().screenManager.howToPlay);
        addTransactionButton(root, "Credits", 300, 500, Game.getInstance().screenManager.credits);

        addButton(root, "Challenge Editor", 300, 350, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Screen editorScreen = Game.getInstance().screenManager.challengeEditor;
                Game.getInstance().challengeManager.initChallengeData();
                Game.getInstance().setScreen(editorScreen);
            }
        });
    }
    
    private void drawUi(GraphicsContext graphics) {
        Image background = new Image(FileUtils.getInputStream(
                "/com/oops/wallsandwarriors/resources/images/background.png"));
        graphics.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(180, 90, 470, 80, 50, 50);
        graphics.fillRoundRect(280, 230, 200, 320, 20, 20);
        
        Font titleFont = Font.font("Arial", FontWeight.BOLD, 54);
        graphics.setFill(Color.WHITE );
        graphics.setStroke(Color.BLACK );
        graphics.setLineWidth(2);
        graphics.setFont(titleFont);
        graphics.fillText("Walls & Warriors", 200, 150);
        graphics.strokeText("Walls & Warriors", 200, 150);
    }
    
}
