package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.GameConstants;
import javafx.scene.canvas.GraphicsContext;

/**
 * A class to implement palette view of the challenge editor
 * @author Emin Bahadir Tuluce
 */
public class EditorPaletteView extends GamePaletteView {
    
    /**
     * Draws the editor palette view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawSinglePalette(graphics, 
                GameConstants.PALETTE_X, GameConstants.PALETTE_Y,
                GameConstants.EDITOR_PALETTE_WIDTH, GameConstants.PALETTE_HEIGHT);
    }
    
}
