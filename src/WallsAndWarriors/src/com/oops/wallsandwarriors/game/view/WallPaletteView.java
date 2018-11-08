package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.GameConstants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallPaletteView implements ViewObject {

    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(GameConstants.PALETTE_X, GameConstants.PALETTE_Y,
                GameConstants.PALETTE_WIDTH, GameConstants.PALETTE_HEIGHT, 50, 50);
        
    }

}
