package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallBastion;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.model.WallPortion;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.MathUtils;
import com.oops.wallsandwarriors.util.Point;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallView extends GridPieceView {
    
    private final WallData model;
    private final MultiRectangleBounds bounds;
    
    public WallView(WallData model, double gridX, double gridY, double gridB) {
        this.model = model;
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridB = gridB;
        bounds = new MultiRectangleBounds();
        index = Game.getInstance().challengeManager.getChallengeData().walls.indexOf(model);
    }
    
    public WallView(WallData model) {
        this(model, GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
    }
    
    public WallView(WallData model, boolean inEditor) {
        this(model);
        if (inEditor) {
            this.gridX = GameConstants.EDITOR_GRID_X;
            this.gridY = GameConstants.EDITOR_GRID_Y;
            this.gridB = GameConstants.EDITOR_GRID_B;
        }
    }
    
    @Override
    public WallData getModel() {
        return model;
    }
    
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        bounds.clearBounds();
        if (model.getPosition() == null) {
            Rectangle box = GamePaletteView.getPaletteBox(index);
            int centerX = (int) (box.x + box.width / 2 - GameConstants.PALETTE_B / 2);
            int centerY = (int) (box.y + box.height / 2 - GameConstants.PALETTE_B / 2);
            GamePaletteView.drawPaletteFrame(graphics, box, isSelected);
            bounds.addBound(box);
            if (isSelected) {
                // Draw the wall on the mouse
                DrawUtils.setAttributes(graphics,
                        previewSuitable ? Color.GREEN : Color.RED, Color.WHITE, 1);
                setParameters(dragX - gridB / 2, dragY - gridB / 2, gridB);
            } else {
                // Draw a lined wall on the box
                DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
                setParameters(centerX, centerY, GameConstants.PALETTE_B);
            }
        } else {
            // Draw the wall on its actual position
            DrawUtils.setAttributes(graphics, Color.BLACK, Color.WHITE, 1);
            setParameters(gridX, gridY, gridB);
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
    
    private void drawWallPortion(GraphicsContext graphics, Coordinate block1, Coordinate block2, boolean addBounds) {
        double a = screenX + (block1.x + 0.5) * blockLength;
        double b = screenY + (block1.y + 0.5) * blockLength;
        double c = screenX + (block2.x + 0.5) * blockLength;
        double d = screenY + (block2.y + 0.5) * blockLength;
        double l = blockLength;
        Point difference = new Point(Math.abs(a - c), Math.abs(b - d));
        final Point HORIZONTAL = new Point(l, 0);
        final Point VERTICAL = new Point(0, l);
        double thickness = blockLength * GameConstants.WALL_THICKNESS;
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
                    bounds.addBound(new Rectangle(x, y, w, h));
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
                    bounds.addBound(new Rectangle(x, y, w, h));
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
        final double RAD_RATIO = GameConstants.BASTION_RADIUS;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
        if (addBounds) {
            bounds.addBound(new Rectangle( a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO));
        }
    }

}
