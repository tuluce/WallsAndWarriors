package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.util.DrawUtils;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallPaletteView implements ViewObject {
    
    private int selectedWallIndex;
    private WallView[] wallPictures;
    
    public void update(int selectedWallIndex) {
        this.selectedWallIndex = selectedWallIndex;
        int wallNum = Game.getInstance().getBoundsManager()
                .getPaletteBounds().size();
        wallPictures = new WallView[wallNum];
        for (int i = 0; i < wallNum; i++) {
            wallPictures[i] = new WallView();
        }
    }

    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        List<Rectangle2D.Double> paletteBounds = Game.getInstance()
                .getBoundsManager().getPaletteBounds();
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(GameConstants.PALETTE_X, GameConstants.PALETTE_Y,
                GameConstants.PALETTE_WIDTH, GameConstants.PALETTE_HEIGHT, 50, 50);
        List<WallData> walls = Game.getInstance().getChallengeManager()
                .getChallengeData().walls;
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).getPosition() == null) {
                if (selectedWallIndex == i) {
                    graphics.setFill(Color.YELLOW);
                } else {
                    graphics.setFill(Color.WHITE);
                }
                Rectangle2D.Double rectangle = paletteBounds.get(i);
                rectangle.setRect(rectangle.x + GameConstants.PALETTE_MARGIN,
                        rectangle.y + GameConstants.PALETTE_MARGIN,
                        rectangle.width - 2* GameConstants.PALETTE_MARGIN,
                        rectangle.height - 2* GameConstants.PALETTE_MARGIN);
                graphics.setStroke(Color.LIGHTGRAY);
                graphics.setLineWidth(8);
                DrawUtils.drawRoundRect(graphics, rectangle.x, rectangle.y,
                        rectangle.width, rectangle.height, 50);
                if (selectedWallIndex != i) {
                    graphics.setStroke(Color.BLACK);
                    graphics.setLineWidth(1);
                    int centerX = (int) (rectangle.x + rectangle.width / 2 -
                            GameConstants.PALETTE_B / 2);
                    int centerY = (int) (rectangle.y + rectangle.height / 2 -
                            GameConstants.PALETTE_B / 2);
                    wallPictures[i].update(centerX, centerY,
                            (int) GameConstants.PALETTE_B, walls.get(i), Coordinate.ZERO);
                    wallPictures[i].draw(graphics, deltaTime);
                }
            }
        }
    }

}
