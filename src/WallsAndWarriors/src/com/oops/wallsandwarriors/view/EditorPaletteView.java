package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.GameConstants;
import javafx.scene.canvas.GraphicsContext;

public class EditorPaletteView extends GamePaletteView {
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawSinglePalette(graphics, 
                GameConstants.PALETTE_X, GameConstants.PALETTE_Y,
                GameConstants.EDITOR_PALETTE_WIDTH, GameConstants.PALETTE_HEIGHT);
    }
    
}
