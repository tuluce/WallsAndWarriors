package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.DrawUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A class to implement Fps display view
 * @author Emin Bahadir Tuluce
 */
public class FpsDisplayView implements ViewObject {

    private static final double REFRESH_PERIOD = 1;
    
    private final Font fpsFont;
    
    private double time;
    private int frames;
    private int fps;

    /**
     * A default constructor of FpsDisplayView
     */
    public FpsDisplayView() {
        fpsFont = Font.font("Monospace", FontWeight.BOLD, 10);
        time = 0;
    }
    
    /**
     * Draws the fps display view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        time += deltaTime;
        frames++;
        if (time > REFRESH_PERIOD) {
            fps = (int) (frames / time) + 1;
            time = 0;
            frames = 0;
        }
        DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
        graphics.setFont(fpsFont);
        if (fps > 0) {
            graphics.fillText(fps + " fps", 5, GameConstants.SCREEN_HEIGHT - 5);
        }
    }

}
