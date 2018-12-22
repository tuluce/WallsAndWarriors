package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Point;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.oops.wallsandwarriors.model.HighTowerData;

/**
 * A class to implement high tower view
 * @author  Emin Bahadir Tuluce
 */
public class HighTowerView extends GridPieceView {
    
    private final HighTowerData model;
    private final MultiRectangleBounds bounds;
    private boolean isPlaced;
    private boolean onPalette;

    /**
     * A constructor of the high tower class with given parameters
     * @param gridB length of the grid block
     * @param model high tower model
     * @param gridX x coordinate
     * @param gridY y coordinate
     */
    public HighTowerView(HighTowerData model, double gridX, double gridY, double gridB) {
        this.model = model;
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridB = gridB;
        bounds = new MultiRectangleBounds();
    }

    /**
     * A constructor of the high tower class with parameter
     * @return list of block bounds
     */
    public HighTowerView(HighTowerData model) {
        this(model, GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
    }

    /**
     * A constructor of the high tower class with parameters
     * @param model high tower model
     * @param inEditor indicates in editor or not
     */
    public HighTowerView(HighTowerData model, boolean inEditor) {
        this(model);
        if (inEditor) {
            this.gridX = GameConstants.EDITOR_GRID_X;
            this.gridY = GameConstants.EDITOR_GRID_Y;
            this.gridB = GameConstants.EDITOR_GRID_B;
        }
    }
    
    @Override
    public HighTowerData getModel() {
        return model;
    }
    
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        bounds.clearBounds();
        isPlaced = true;
        onPalette = false;
        if (model.getFirstPosition() == null || model.getSecondPosition() == null) {
            isPlaced = false;
            Rectangle box = GamePaletteView.getPaletteBox(index);
            int centerX = (int) (box.x + box.width / 2 - GameConstants.EDITOR_GRID_B / 2);
            int centerY = (int) (box.y + box.height / 2 - GameConstants.EDITOR_GRID_B / 2);
            GamePaletteView.drawPaletteFrame(graphics, box, isSelected);
            bounds.addBound(box);
            if (isSelected) {
                setParameters(dragX - gridB / 2, dragY - gridB / 2, gridB);
                DrawUtils.setAttributes(graphics,
                        previewSuitable ? Color.BLACK : Color.DARKGRAY, getColor(), 1);
            } else {
                onPalette = true;
                setParameters(centerX, centerY, GameConstants.EDITOR_GRID_B);
                DrawUtils.setAttributes(graphics, Color.BLACK, getColor(), 1);
            }
        } else {
            setParameters(gridX, gridY, gridB);
            DrawUtils.setAttributes(graphics, Color.BLACK, getColor(), 1);
        }
        
        graphics.setLineWidth(blockLength / 3.0);
        drawHighTowerWall(graphics);
        graphics.setLineWidth(6);
        drawHighTowerPart(graphics, getFirstPoint());
        drawHighTowerPart(graphics, getSecondPoint());
    }

    /**
     * A method to draw high tower part
     * @param graphics Graphics object
     * @param part  Point object
     */
    private void drawHighTowerPart(GraphicsContext graphics, Point part) {
        double a = screenX + (part.x + 0.5) * blockLength;
        double b = screenY + (part.y + 0.5) * blockLength;
        double l = blockLength;
        final double RAD_RATIO = 0.6;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
        if (isPlaced) {
            bounds.addBound(new Rectangle(a - l * RAD_RATIO * 0.5,
                    b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO));
        }
    }

    /**
     * A method to draw high tower wall
     * @param graphics Graphics object
     */
    private void drawHighTowerWall(GraphicsContext graphics) {
        Point first = getFirstPoint();
        Point second = getSecondPoint();
        double a = screenX + (first.x + 0.5) * blockLength;
        double b = screenY + (first.y + 0.5) * blockLength;
        double c = screenX + (second.x + 0.5) * blockLength;
        double d = screenY + (second.y + 0.5) * blockLength;
        graphics.strokeLine(a, b, c, d);
    }

    /**
     * A method to get first point
     * @return first point
     */
    private Point getFirstPoint() {
        if (isPlaced) {
            return new Point(model.getFirstPosition());
        } else if (onPalette) {
            return new Point(-0.4, 0);
        }
        return new Point(0, 0);
    }

    /**
     * A method to get second point
     * @return second point
     */
    private Point getSecondPoint() {
        if (isPlaced) {
            return new Point(model.getSecondPosition());
        } else if (onPalette) {
            return new Point(0.4, 0);
        } else if (model.isVertical) {
            return new Point(0, 1);
        }
        return new Point(1, 0);
    }

    /**
     * A method to get color
     * @return high tower color
     */
    private Color getColor() {
        return Game.getInstance().settingsManager.getAllyColor();
    }
    
}
