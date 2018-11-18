package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.KnightData;

public class KnightView extends GridPieceView {

    private final KnightData model;
    private final RectangleBounds bounds;
    
    public KnightView(KnightData model, double gridX, double gridY, double gridB) {
        this.model = model;
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridB = gridB;
        bounds = new RectangleBounds();
    }
    
    public KnightView(KnightData model) {
        this(model, GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
    }
    
    public KnightView(KnightData model, boolean inEditor) {
        this(model);
        if (inEditor) {
            this.gridX = GameConstants.EDITOR_GRID_X;
            this.gridY = GameConstants.EDITOR_GRID_Y;
            this.gridB = GameConstants.EDITOR_GRID_B;
        }
    }
    
    @Override
    public KnightData getModel() {
        return model;
    }
    
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        boolean setBounds = true;
        boolean drawCross = false;
        if (model.getPosition() == null) {
            Rectangle box = GamePaletteView.getPaletteBox(index);
            int centerX = (int) (box.x + box.width / 2 - GameConstants.EDITOR_GRID_B / 2);
            int centerY = (int) (box.y + box.height / 2 - GameConstants.EDITOR_GRID_B / 2);
            GamePaletteView.drawPaletteFrame(graphics, box, isSelected);
            bounds.setBound(box);
            setBounds = false;
            if (isSelected) {
                DrawUtils.setAttributes(graphics, Color.BLACK, getColor(), 4);
                setParameters(dragX - gridB / 2, dragY - gridB / 2, gridB);
                drawCross = !previewSuitable;
            } else {
                DrawUtils.setAttributes(graphics, Color.BLACK, getColor(), 4);
                setParameters(centerX, centerY, GameConstants.EDITOR_GRID_B);
            }
        } else {
            DrawUtils.setAttributes(graphics, Color.BLACK, getColor(), 4);
            setParameters(gridX, gridY, gridB);
        }
        
        drawKnight(graphics, setBounds, drawCross);
    }
    
    private void drawKnight(GraphicsContext graphics, boolean setBounds, boolean drawCross) {
        Coordinate currentPosition = (model.getPosition() != null) ? model.getPosition() : Coordinate.ZERO;
        final double RAD_RATIO = 0.5;
        double a = screenX + (currentPosition.x + 0.5) * blockLength;
        double b = screenY + (currentPosition.y + 0.5) * blockLength;
        double x = a - blockLength * RAD_RATIO * 0.5;
        double y = b - blockLength * RAD_RATIO * 0.5;
        double width = blockLength * RAD_RATIO;
        double height = blockLength * RAD_RATIO;
        DrawUtils.drawOval(graphics, x, y, width, height);
        if (drawCross) {
            DrawUtils.setAttributes(graphics, Color.DARKGRAY, Color.WHITE, 5);
            graphics.strokeLine(x, y, x + width, y + height);
            graphics.strokeLine(x + width, y, x, y + height);
        }
        if (setBounds) {
            bounds.setBound(new Rectangle(x, y, width, height));
        }
    }

    
    private Color getColor() {
            return model.isEnemy ? Game.getInstance().settingsManager.getEnemyColor():
                    Game.getInstance().settingsManager.getAllyColor();
    }
    
}
