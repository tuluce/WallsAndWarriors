package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridView implements ViewObject {
    
    private final double screenX;
    private final double screenY;
    private final double margin;
    private final double blockLength;
    private List<Rectangle> blockBounds;
    
    public GridView(double x, double y, double margin, double blockLength) {
        this.screenX = x;
        this.screenY = y;
        this.margin = margin;
        this.blockLength = blockLength;
        calculateBlockBounds();
    }
    
    public List<Rectangle> getBlockBounds() {
        return blockBounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawGrid(graphics);
    }
    
    private void drawGrid(GraphicsContext graphics) {
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(screenX - margin,
                screenY - margin,
                5 * blockLength + 2 * margin,
                4 * blockLength + 2 * margin, 50, 50);
        List<Coordinate> blocks = Game.getInstance().challengeManager.getChallengeData().blocks;
        DrawUtils.setAttributes(graphics, Color.GRAY, Color.LIGHTGRAY, 6);
        for (Coordinate block : blocks) {
            DrawUtils.drawRect(graphics, 
                    screenX + block.x * blockLength,
                    screenY + block.y * blockLength,
                    blockLength, blockLength);
        }
    }
    
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
    
    private List<Coordinate> getExtendedBlocks() {
        List<Coordinate> blocks = Game.getInstance().challengeManager.getChallengeData().blocks;
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
    
    public double translateToScreenY(double y) {
        return screenY + y * blockLength;
    }
    
    public double translateToScreenX(double x) {
        return screenX + x * blockLength;
    }
    
    public int translateToGridY(double y) {
        return (int) ((y - screenY) / blockLength);
    }

    public int translateToGridX(double x) {
        return (int) ((x - screenX) / blockLength);
    }

}
