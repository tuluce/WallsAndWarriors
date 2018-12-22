package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.FileUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;

/**
 * This class will structure the credits screen. It will add the GUI components for this
 * screen including the credits information and the main menu button.
 * @author Merve Sagyatanlar
 */
public class CreditsScreen extends GeneralScreen {

    /**
     * An overriden getScene method to return the current Screen.
     * @return the current screen as a Screen object.
     */
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        GraphicsContext g = addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png", "Credits");
        g.setFill(Color.BEIGE);
        g.fillRoundRect(50, 80, 450, 450, 30, 30);
        Image content = getContentImage();
        g.drawImage(content, 60, 90, 780, 430);
        addTransitionButton(root, "Back", GameConstants.BACK_BUTTON_X,
                GameConstants.BACK_BUTTON_Y, Game.getInstance().screenManager.mainMenu);

        return scene;
    }
    
    private static Image contentImage = null;
    private static Image getContentImage() {
        if (contentImage == null) {
            contentImage = new Image(FileUtils.getInputStream("/com/oops/wallsandwarriors/resources/images/credits.png"));
        }
        return contentImage;
    }
}
