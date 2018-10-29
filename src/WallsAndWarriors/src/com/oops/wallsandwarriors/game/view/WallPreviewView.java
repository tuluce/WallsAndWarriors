package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallPreviewView implements ViewObject {
    
    private final WallView wallView;
    
    private int selectedWallIndex;
    private Coordinate hoveredBlock;
    private boolean placementIsSuitable;
    private double lastMouseX;
    private double lastMouseY;
    
    public WallPreviewView() {
        wallView = new WallView();
    }
    
    public void update(int selectedWallIndex, Coordinate hoveredBlock, 
            boolean placementIsSuitable, double lastMouseX, double lastMouseY) {
        this.selectedWallIndex = selectedWallIndex;
        this.hoveredBlock = hoveredBlock;
        this.placementIsSuitable = placementIsSuitable;
        this.lastMouseX = lastMouseX;
        this.lastMouseY = lastMouseY;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        if (selectedWallIndex >= 0) {
            WallData wall = Game.getInstance().getChallengeManager()
                    .getChallengeData().walls.get(selectedWallIndex);
            graphics.setFill(Color.YELLOW);
            if (placementIsSuitable) {
                graphics.setStroke(Color.GREEN);
            } else {
                graphics.setStroke(Color.RED);
            }
            if (hoveredBlock != null) {
                wallView.update(GameConstants.GRID_X,
                        GameConstants.GRID_Y, GameConstants.GRID_B,
                        wall, hoveredBlock);
            } else {
                graphics.setStroke(Color.BLACK);
                wallView.update(
                        (int) lastMouseX - GameConstants.GRID_B / 2,
                        (int) lastMouseY - GameConstants.GRID_B / 2,
                        GameConstants.GRID_B, wall, Coordinate.ZERO);
            }
            wallView.draw(graphics, deltaTime);
        }
    }
    
}
