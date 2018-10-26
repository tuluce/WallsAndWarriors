package com.oops.wallsandwarriors.screens;

import static com.oops.wallsandwarriors.GameConstants.SCREEN_HEIGHT;
import static com.oops.wallsandwarriors.GameConstants.SCREEN_WIDTH;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.FileUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class ParentScreen implements Screen {
    
    protected void addButton(Group root, String text, double x, double y, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        setButtonCoordinates(button, x , y);
        button.setOnAction(action);
        root.getChildren().add(button);
    }
    
    protected void addButton(Group root, String text, double x, double y) {
        addButton(root, text, x, y, null);
    }
    
    protected void addTransactionButton(Group root, String text, double x, double y, Screen nextScreen) {
        addButton(root, text, x, y, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Game.getInstance().setScreen(nextScreen);
                }
            }
        );
    }
    
    protected void setButtonCoordinates(Button button, double x, double y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
    
    protected void renderBackgroundCanvas(Group root, String imagePath, String title) {
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image background = new Image(FileUtils.getInputStream(imagePath));
        gc.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        gc.setFill(Color.WHITE );
        gc.setStroke(Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText(title, 30, 50);
        gc.strokeText(title, 30, 50);
        
        root.getChildren().add(canvas);
    }
    
}
