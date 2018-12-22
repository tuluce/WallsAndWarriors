package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class to implement grid view
 * @author Emin Bahadir Tuluce
 */
public class GridView implements ViewObject {
    
    private final double screenX;
    private final double screenY;
    private final double margin;
    private final double blockLength;
    private List<Rectangle> blockBounds;

    /**
     * A constructor of the grid view class
     * @param x coordinate value
     * @param y coordinate value
     * @param margin  margin coordinate value
     * @param blockLength length of the block
     */
    public GridView(double x, double y, double margin, double blockLength) {
        this.screenX = x;
        this.screenY = y;
        this.margin = margin;
        this.blockLength = blockLength;
        calculateBlockBounds();
    }

    /**
     * A method to get block bounds
     * @return list of block bounds
     */
    public List<Rectangle> getBlockBounds() {
        return blockBounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawGrid(graphics);
    }

    /**
     * A method draw grid
     * @param graphics Graphics object
     */
    private void drawGrid(GraphicsContext graphics) {
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(screenX - margin,
                screenY - margin,
                5 * blockLength + 2 * margin,
                4 * blockLength + 2 * margin, 50, 50);
        List<Coordinate> blocks = Game.getInstance().
                challengeManager.getChallengeData().blocks;
        DrawUtils.setAttributes(graphics, Color.GRAY, Color.LIGHTGRAY, 6);
        for (Coordinate block : blocks) {
            DrawUtils.drawRect(graphics, 
                    screenX + block.x * blockLength,
                    screenY + block.y * blockLength,
                    blockLength, blockLength);
        }
    }

    /**
     * A method to calculate  block bounds
     */
    private void calculateBlockBounds() {
        blockBounds = new ArrayList<Rectangle>();
        List<Coordinate> extendedBlocks = getExtendedBlocks();
        for (Coordinate block : extendedBlocks) {
            blockBounds.add(new Rectangle(
                    translateToScreenX(block.x),
                    translateToScreenY(block.y),
                    blockLength, blockLength));
        }
    }

    /**
     * A method to get extended blocks
     * @return list of extended bound coordinates
     */
    private List<Coordinate> getExtendedBlocks() {
        List<Coordinate> blocks = Game.getInstance().
                challengeManager.getChallengeData().blocks;
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

    /**
     * A method to translate screen y coordinate
     * @param y  coordinate value
     * @return translated y coordinate
     */
    public double translateToScreenY(double y) {
        return screenY + y * blockLength;
    }

    /**
     * A method to translate screen x coordinate
     * @param x  coordinate value
     * @return translated x value
     */
    public double translateToScreenX(double x) {
        return screenX + x * blockLength;
    }

    /**
     * A method to translate grid y coordinate
     * @param y coordinate value
     * @return translated y value
     */
    public int translateToGridY(double y) {
        return (int) ((y - screenY) / blockLength);
    }

    /**
     * A method to get block bounds
     * @param x coordinate value
     * @return translated x value
     */
    public int translateToGridX(double x) {
        return (int) ((x - screenX) / blockLength);
    }

}
