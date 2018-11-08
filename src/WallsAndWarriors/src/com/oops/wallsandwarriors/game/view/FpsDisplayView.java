package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.util.DrawUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FpsDisplayView implements ViewObject {

    private static final double REFRESH_PERIOD = 0.5;
    
    private final Font fpsFont;
    
    private double lastRefreshTime;
    private int fps;
    
    public FpsDisplayView() {
        fpsFont = Font.font("Monospace", FontWeight.BOLD, 10);
        lastRefreshTime = 0;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        lastRefreshTime += deltaTime;
        if (lastRefreshTime > REFRESH_PERIOD) {
            fps = (int) (1.0 / deltaTime);
            lastRefreshTime = 0;
        }
        DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
        graphics.setFont(fpsFont);
        graphics.fillText(fps + " fps", 10, 590);
    }

}
