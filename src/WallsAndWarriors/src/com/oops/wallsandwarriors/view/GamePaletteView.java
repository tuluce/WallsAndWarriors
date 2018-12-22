package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class to implement palette view of the game
 * @author Emin Bahadir Tuluce
 */
public class GamePaletteView implements ViewObject {

    /**
     * Draws the game palette view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawSinglePalette(graphics, 
                GameConstants.PALETTE_X, GameConstants.PALETTE_Y,
                GameConstants.PALETTE_WIDTH, GameConstants.PALETTE_HEIGHT);
    }

    /**
     * A method to draw a single palette
     * @param graphics graphics object
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the palette
     * @param height height of the palette
     */
    protected void drawSinglePalette(GraphicsContext graphics,
            double x, double y, double width, double height) {
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(x, y, width, height, 50, 50);
    }

    /**
     * A method to draw palette frame
     * @param graphics graphics object
     * @param box rectangle object
     * @param isSelected boolean value to indicate selected or not
     */
    protected static void drawPaletteFrame(GraphicsContext graphics,
                                           Rectangle box, boolean isSelected) {
        if (isSelected) {
            DrawUtils.setAttributes(graphics, Color.LIGHTGRAY,
                    Color.YELLOW, 8);
        } else {
            DrawUtils.setAttributes(graphics, Color.LIGHTGRAY,
                    Color.WHITE, 8);
        }
        DrawUtils.drawRoundRect(graphics,
                box.x + GameConstants.PALETTE_MARGIN,
                box.y + GameConstants.PALETTE_MARGIN,
                box.width - 2 * GameConstants.PALETTE_MARGIN,
                box.height - 2 * GameConstants.PALETTE_MARGIN, 50);
    }

    /**
     * A method to get palette box
     * @param index  index value
     * @return palette box
     */
    protected static Rectangle getPaletteBox(int index) {
        if (index == -1) {
            return new Rectangle(-1, -1, 0, 0);
        }
        double boxHeight = GameConstants.PALETTE_ELEMENT_HEIGHT;
        double boxWidth = GameConstants.PALETTE_WIDTH;
        Rectangle box = new Rectangle(
                GameConstants.PALETTE_X + (
                        index / GameConstants.PALETTE_ELEMENT_NO) *
                        (boxWidth + GameConstants.PALETTE_MARGIN),
                GameConstants.PALETTE_Y + (
                        index % GameConstants.PALETTE_ELEMENT_NO) * boxHeight,
                boxWidth, boxHeight);
        return box;
    }

}
