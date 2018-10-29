package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallBastion;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.model.WallPortion;
import com.oops.wallsandwarriors.util.DrawUtils;
import java.awt.geom.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallView implements ViewObject {
    
    private double x;
    private double y;
    private double blockLength;
    private WallData wall;
    private Coordinate position;
    
    public WallView() {}
    
    public WallView(double x, double y, double blockLength, WallData wall, Coordinate position) {
        update(x, y, blockLength, wall, position);
    }
    
    public void update(double x, double y, double blockLength, WallData wall, Coordinate position) {
        this.x = x;
        this.y = y;
        this.blockLength = blockLength;
        this.wall = wall;
        this.position = position;
    }
    
    public void update(double x, double y, double blockLength, WallData wall) {
        update(x, y, blockLength, wall, wall.getPosition());
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        for (WallPortion portion : wall.getWallDefinition().portions) {
            Coordinate block1 = position.plus(portion.firstRelativePos);
            Coordinate block2 = position.plus(portion.secondRelativePos);
            drawWallPortion(graphics, block1, block2);
        }
        for (WallBastion bastion : wall.getWallDefinition().bastions) {
            Coordinate block = position.plus(bastion.relativePos);
            drawWallBastion(graphics, block);
        }
    }
    
    private void drawWallPortion(GraphicsContext graphics, Coordinate block1, Coordinate block2) {
        double a = x + (block1.x + 0.5) * blockLength;
        double b = y + (block1.y + 0.5) * blockLength;
        double c = x + (block2.x + 0.5) * blockLength;
        double d = y + (block2.y + 0.5) * blockLength;
        double l = blockLength;
        Point2D.Double difference = new Point2D.Double(Math.abs(a - c), Math.abs(b - d));
        final Point2D.Double HORIZONTAL = new Point2D.Double(l, 0);
        final Point2D.Double VERTICAL = new Point2D.Double(0, l);
        final double THICKNESS = 0.1;
        double thickness = blockLength * THICKNESS;
        graphics.setLineWidth(5);
        graphics.setFill(Color.WHITE);
        if (difference.equals(HORIZONTAL)) {
            if (blockLength > 45) {
                double x1 = (a + c) / 2;
                double y1 = b - l / 2;
                DrawUtils.drawRoundRect(graphics, x1 - thickness / 2, y1, thickness, blockLength, 20);
            } else {
                graphics.strokeLine((a + c) / 2, b - l / 2, (a + c) / 2, b + l / 2);
            }
        } else if (difference.equals(VERTICAL)) {
            if (blockLength > 45) {
                double x1 = a - l / 2;
                double y1 = (b + d) / 2;
                DrawUtils.drawRoundRect(graphics, x1, y1 - thickness / 2, blockLength, thickness, 20);
            } else {
                graphics.strokeLine(a - l / 2, (b + d) / 2, a + l / 2, (b + d) / 2);
            }
        } else {
            throw new RuntimeException("Unexpected wall difference: " + difference);
        }
        
    }
    
    private void drawWallBastion(GraphicsContext graphics, Coordinate block) {
        graphics.setFill(Color.WHITE);
        double a = x + block.x * blockLength;
        double b = y + block.y * blockLength;
        double l = blockLength;
        final double RAD_RATIO = 0.3;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
    }

}
