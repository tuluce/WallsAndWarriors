package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallBastion;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.model.WallPortion;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.MathUtils;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallView implements BoundedViewObject {
    
    // Properties
    private final WallData model;
    private final WallBounds bounds;
    
    // State
    private boolean isSelected;
    private boolean previewSuitable;
    private double dragX;
    private double dragY;
    
    // Parameters
    private double screenX;
    private double screenY;
    private double blockLength;
    
    public WallView(WallData model) {
        this.model = model;
        bounds = new WallBounds();
    }
    
    public WallData getModel() {
        return model;
    }
    
    public void update(boolean isSelected, boolean previewSuitable, double dragX, double dragY) {
        this.isSelected = isSelected;
        this.previewSuitable = previewSuitable;
        this.dragX = dragX;
        this.dragY = dragY;
    }
    
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        bounds.clearBounds();
        if (model.getPosition() == null) {
            Rectangle2D.Double box = getPaletteBox();
            int centerX = (int) (box.x + box.width / 2 - GameConstants.PALETTE_B / 2);
            int centerY = (int) (box.y + box.height / 2 - GameConstants.PALETTE_B / 2);
            drawPaletteFrame(graphics, box);
            if (isSelected) {
                // Draw the wall on the mouse
                DrawUtils.setAttributes(graphics, previewSuitable ? Color.GREEN : Color.RED, Color.WHITE, 1);
                setParameters(dragX - GameConstants.GRID_B / 2,
                        dragY - GameConstants.GRID_B / 2, GameConstants.GRID_B);
            } else {
                // Draw a lined wall on the box
                DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
                setParameters(centerX, centerY, GameConstants.PALETTE_B);
            }
        } else {
            // Draw the wall on its actual position
            DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
            setParameters(GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
        }
        
        Coordinate position = model.getPosition();
        if (position == null) {
            position = Coordinate.ZERO;
        }
        
        for (WallPortion portion : model.getWallDefinition().portions) {
            Coordinate block1 = position.plus(portion.firstRelativePos);
            Coordinate block2 = position.plus(portion.secondRelativePos);
            drawWallPortion(graphics, block1, block2, model.getPosition() != null);
        }
        for (WallBastion bastion : model.getWallDefinition().bastions) {
            Coordinate block = position.plus(bastion.relativePos);
            drawWallBastion(graphics, block, model.getPosition() != null);
        }
    }
    
    private void setParameters(double screenX, double screenY, double blockLength) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.blockLength = blockLength;
    }
    
    private Rectangle2D.Double getPaletteBox() {
        ChallengeData challenge = Game.getInstance().getChallengeManager().getChallengeData();
        int wallNum = challenge.walls.size();
        int index = challenge.walls.indexOf(model);
        double boxHeight = GameConstants.PALETTE_HEIGHT / wallNum;
        double boxWidth = GameConstants.PALETTE_WIDTH;
        Rectangle2D.Double box = new Rectangle2D.Double(GameConstants.PALETTE_X,
                GameConstants.PALETTE_Y + index * boxHeight, boxWidth, boxHeight);
        return box;
    }
    
    private void drawPaletteFrame(GraphicsContext graphics, Rectangle2D.Double box) {
        if (isSelected) {
            DrawUtils.setAttributes(graphics, Color.LIGHTGRAY, Color.YELLOW, 8);
        } else {
            DrawUtils.setAttributes(graphics, Color.LIGHTGRAY, Color.WHITE, 8);
        }
        DrawUtils.drawRoundRect(graphics,
                box.x + GameConstants.PALETTE_MARGIN,
                box.y + GameConstants.PALETTE_MARGIN,
                box.width - 2 * GameConstants.PALETTE_MARGIN,
                box.height - 2 * GameConstants.PALETTE_MARGIN, 50);
        bounds.addBound(box);
    }
    
    private void drawWallPortion(GraphicsContext graphics, Coordinate block1, Coordinate block2, boolean addBounds) {
        double a = screenX + (block1.x + 0.5) * blockLength;
        double b = screenY + (block1.y + 0.5) * blockLength;
        double c = screenX + (block2.x + 0.5) * blockLength;
        double d = screenY + (block2.y + 0.5) * blockLength;
        double l = blockLength;
        Point2D.Double difference = new Point2D.Double(Math.abs(a - c), Math.abs(b - d));
        final Point2D.Double HORIZONTAL = new Point2D.Double(l, 0);
        final Point2D.Double VERTICAL = new Point2D.Double(0, l);
        final double THICKNESS = 0.1;
        double thickness = blockLength * THICKNESS;
        graphics.setLineWidth(5);
        graphics.setFill(Color.WHITE);
        if (MathUtils.equals(difference, HORIZONTAL)) {
            if (blockLength > 45) {
                double x = ((a + c) / 2) - (thickness / 2);
                double y = b - l / 2;
                double w = thickness;
                double h = blockLength;
                DrawUtils.drawRoundRect(graphics, x, y, w, h, 20);
                if (addBounds) {
                    bounds.addBound(new Rectangle2D.Double(x, y, w, h));
                }
            } else {
                graphics.strokeLine((a + c) / 2, b - l / 2, (a + c) / 2, b + l / 2);
            }
        } else if (MathUtils.equals(difference, VERTICAL)) {
            if (blockLength > 45) {
                double x = a - l / 2;
                double y = ((b + d) / 2) - (thickness / 2);
                double w = blockLength;
                double h = thickness;
                DrawUtils.drawRoundRect(graphics, x, y, w, h, 20);
                if (addBounds) {
                    bounds.addBound(new Rectangle2D.Double(x, y, w, h));
                }
            } else {
                graphics.strokeLine(a - l / 2, (b + d) / 2, a + l / 2, (b + d) / 2);
            }
        } else {
            throw new RuntimeException("Unexpected wall difference: " + difference);
        }
    }
    
    private void drawWallBastion(GraphicsContext graphics, Coordinate block, boolean addBounds) {
        graphics.setFill(Color.WHITE);
        double a = screenX + block.x * blockLength;
        double b = screenY + block.y * blockLength;
        double l = blockLength;
        final double RAD_RATIO = 0.3;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
        if (addBounds) {
            bounds.addBound(new Rectangle2D.Double( a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO));
        }
    }

}
