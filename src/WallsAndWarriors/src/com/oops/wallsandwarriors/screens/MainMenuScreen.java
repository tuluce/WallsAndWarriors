package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_HEIGHT;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_WIDTH;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.screens.game.GameScreen;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.FileUtils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    
    private ChallengeData lastSessionChallenge;
    private ChallengeData lastSessionHint;
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        
        DebugUtils.initClickDebugger(scene);
        GraphicsContext graphics = addBackgroundCanvas(root);
        checkLastSession();
        drawUi(graphics);
        addButtons(root);
        
        return scene;
    }

    private void addButtons(Group root) {
        addTransitionButton(root, "Campaign Mode", 300, 250, 160, 40, Game.getInstance().screenManager.campaignChallenges);
        addTransitionButton(root, "Custom Challenges", 300, 300, 160, 40, Game.getInstance().screenManager.customChallenges);
        addTransitionButton(root, "Settings", 300, 400, 160, 40, Game.getInstance().screenManager.settings);
        addTransitionButton(root, "How to Play", 300, 450, 160, 40, Game.getInstance().screenManager.howToPlay);
        addTransitionButton(root, "Credits", 300, 500, 160, 40, Game.getInstance().screenManager.credits);
        addButton(root, "Challenge Editor", 300, 350, 160, 40, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Screen editorScreen = Game.getInstance().screenManager.challengeEditor;
                Game.getInstance().challengeManager.initChallengeData();
                Game.getInstance().setScreen(editorScreen);
            }
        });
        addLastSessionButton(root);
    }
    
    private void checkLastSession() {
        try {
            if(Game.getInstance().storageManager.sessionData != null) {
                BufferedReader sessionReader = Game.getInstance().storageManager.getSessionReader();
                if (sessionReader != null) {
                    String challengeCode = sessionReader.readLine();
                    String hintCode = sessionReader.readLine();
                    sessionReader.close();
                    if (challengeCode != null && hintCode != null &&
                        !challengeCode.isEmpty() && !hintCode.isEmpty()) {
                        lastSessionChallenge = EncodeUtils.decode(challengeCode);
                        lastSessionHint = EncodeUtils.decode(hintCode);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void addLastSessionButton(Group root) {
        if (lastSessionChallenge != null) {
            addButton(root, "Continue Last Game", 550, 250, 160, 40, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Screen gameScreen = Game.getInstance().screenManager.gameScreen;
                    ((GameScreen) gameScreen).setPreviousScreen(MainMenuScreen.this);
                    Game.getInstance().challengeManager.setChallengeData(lastSessionChallenge.createCopy(false));
                    Game.getInstance().hintManager.setChallengeData(lastSessionHint.createCopy(false));
                    Game.getInstance().setScreen(gameScreen);
                }
            });
        }
    }
    
    private void drawLastSessionBackground(GraphicsContext graphics) {
        if (lastSessionChallenge != null) {
            graphics.setFill(Color.BEIGE);
            graphics.fillRoundRect(530, 230, 200, 80, 20, 20);
        }
    }
    
    private void drawUi(GraphicsContext graphics) {
        Image background = getContentImage();
        graphics.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(180, 90, 470, 80, 50, 50);
        graphics.fillRoundRect(280, 230, 200, 330, 20, 20);
        drawLastSessionBackground(graphics);
        
        Font titleFont = Font.font("Arial", FontWeight.BOLD, 54);
        graphics.setFill(Color.WHITE );
        graphics.setStroke(Color.BLACK );
        graphics.setLineWidth(2);
        graphics.setFont(titleFont);
        graphics.fillText("Walls & Warriors", 200, 150);
        graphics.strokeText("Walls & Warriors", 200, 150);
    }
    
    private static Image contentImage = null;
    private static Image getContentImage() {
        if (contentImage == null) {
            contentImage = new Image(FileUtils.getInputStream(
                "/com/oops/wallsandwarriors/resources/images/background.png"));
        }
        return contentImage;
    }
    
}
