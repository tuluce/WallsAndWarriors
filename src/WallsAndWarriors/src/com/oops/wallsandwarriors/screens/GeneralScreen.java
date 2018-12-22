package com.oops.wallsandwarriors.screens;

import static com.oops.wallsandwarriors.GameConstants.SCREEN_HEIGHT;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_WIDTH;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.FileUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * Abstract parent class for all Screen classes. It structures the generic
 * elements in all screens like transition buttons and the background.
 * @author OOPs
 */
public abstract class GeneralScreen implements Screen {

    /**
     * A method to add a button to the screen
     * @param root root as a Group object.
     * @param text title text of the button
     * @param x x coordinate of the button to place
     * @param y y coordinate of the button to place
     * @param action parameter to catch the action type when event occurs
     * @return newly created button object
     */
    protected Button addButton(Group root, String text, double x, double y, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        setLayoutPos(button, x , y);
        
        EventHandler<ActionEvent> soundedAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().soundManager.playClick();
                action.handle(event);
            }
        };
        button.setOnAction(soundedAction);
        
        root.getChildren().add(button);
        return button;
    }

    /**
     * A method to add a button to the screen
     * @param root root as a Group object.
     * @param text title text of the button
     * @param x x coordinate of the button to place
     * @param y y coordinate of the button to place
     * @param width minimum width to set for the button
     * @param height minimum height to set for the button
     * @param action parameter to catch the action type when event occurs
     * @return newly created button object
     */
    protected Button addButton(Group root, String text, double x, double y, double width, double height, EventHandler<ActionEvent> action) {
        Button button = addButton(root, text, x, y, action);
        button.setMinWidth(width);
        button.setMinHeight(height);
        return button;
    }

    /**
     * A method to add a button to the screen with minimum specified width and height
     * @param root root as a Group object.
     * @param text title text of the button
     * @param x x coordinate of the button to place
     * @param y y coordinate of the button to place
     * @return newly created button object
     */
    protected Button addButton(Group root, String text, double x, double y) {
        return addButton(root, text, x, y, null);
    }

    /**
     * A method to add a transition button to the screen
     * @param root root as a Group object.
     * @param text title text of the button
     * @param x x coordinate of the button to place
     * @param y y coordinate of the button to place
     * @param nextScreen next screen to pass/show while button is pressed
     * @return newly created transition button object
     */
    protected Button addTransitionButton(Group root, String text, double x, double y, Screen nextScreen) {
        return addButton(root, text, x, y, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().setScreen(nextScreen);
            }
        });
    }

    /**
     * A method to add a transition button to the screen
     * @param root root as a Group object.
     * @param text title text of the button
     * @param x x coordinate of the button to place
     * @param y y coordinate of the button to place
     * @param width minimum width to set for the button
     * @param height minimum height to set for the button
     * @param nextScreen next screen to pass/show while button is pressed
     * @return newly created transition button object
     */
    protected Button addTransitionButton(Group root, String text, double x, double y, double width, double height, Screen nextScreen) {
        Button button = addTransitionButton(root, text, x, y, nextScreen);
        button.setMinWidth(width);
        button.setMinHeight(height);
        return button;
    }

    
    protected void setLayoutPos(Node componentNode, double x, double y) {
        componentNode.setLayoutX(x);
        componentNode.setLayoutY(y);
    }
    
    protected GraphicsContext addBackgroundCanvas(Group root, String imagePath, String title) {
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        
        Image background = new Image(FileUtils.getInputStream(imagePath));
        graphics.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        Font titleFont = Font.font("Arial", FontWeight.BOLD, 48);
        graphics.setFill(Color.WHITE );
        graphics.setStroke(Color.BLACK );
        graphics.setLineWidth(2);
        graphics.setFont(titleFont);
        graphics.fillText(title, 30, 50);
        graphics.strokeText(title, 30, 50);
        
        root.getChildren().add(canvas);
        
        return graphics;
    }
    
    protected GraphicsContext addBackgroundCanvas(Group root) {
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        return graphics;
    }
    
}
