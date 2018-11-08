package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.game.model.Coordinate;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BoundsManager {

    private List<Rectangle2D.Double> blockBounds;
    
    public List<Rectangle2D.Double> getBlockBounds() {
        return blockBounds;
    }
    
    public void calculateBounds() {
        calculateBlockBounds();
    }
    
    private void calculateBlockBounds() {
        blockBounds = new ArrayList<Rectangle2D.Double>();
        List<Coordinate> extendedBlocks = getExtendedBlocks();
        for (Coordinate block : extendedBlocks) {
            blockBounds.add(new Rectangle2D.Double(
                    translateToScreenX(block.x),
                    translateToScreenY(block.y),
                    GameConstants.GRID_B, GameConstants.GRID_B));
        }
    }
    
    private List<Coordinate> getExtendedBlocks() {
        List<Coordinate> blocks = Game.getInstance().getChallengeManager().getChallengeData().blocks;
        List<Coordinate> extendedBlocks = new ArrayList<Coordinate>();
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Coordinate block : blocks) {
            minX = Math.min(minX, block.x);
            minY = Math.min(minY, block.y);
            maxX = Math.max(maxX, block.x);
            maxY = Math.max(maxY, block.y);
        }
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                extendedBlocks.add(new Coordinate(x, y));
            }
        }
        return extendedBlocks;
    }
    
    public static double translateToScreenY(double gridY) {
        return GameConstants.GRID_Y + gridY * GameConstants.GRID_B;
    }

    public static int translateToGridY(double screenY) {
        return (int) ((screenY - GameConstants.GRID_Y) / GameConstants.GRID_B);
    }

    public static int translateToGridX(double screenX) {
        return (int) ((screenX - GameConstants.GRID_X) / GameConstants.GRID_B);
    }

    public static double translateToScreenX(double gridX) {
        return GameConstants.GRID_X + gridX * GameConstants.GRID_B;
    }
    
}
