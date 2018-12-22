package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.KnightData;

/**
 * A class to implement knight view
 * @author Emin Bahadir Tuluce
 */
public class KnightView extends GridPieceView {

    private final KnightData model;
    private final RectangleBounds bounds;

    /**
     * Creates a new KnightView.
     * @param model the model for the knight view
     * @param gridX the grid x position 
     * @param gridY the grid y position
     * @param gridB the length of a grid block
     */
    public KnightView(KnightData model, double gridX, double gridY, double gridB) {
        this.model = model;
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridB = gridB;
        bounds = new RectangleBounds();
    }

    /**
     * Creates a new KnightView.
     * @param model the model for the knight view
     */
    public KnightView(KnightData model) {
        this(model, GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
    }

    /**
     * Creates a new KnightView.
     * @param model the model for the knight view
     * @param inEditor should be given true if the knight is in editor
     */
    public KnightView(KnightData model, boolean inEditor) {
        this(model);
        if (inEditor) {
            this.gridX = GameConstants.EDITOR_GRID_X;
            this.gridY = GameConstants.EDITOR_GRID_Y;
            this.gridB = GameConstants.EDITOR_GRID_B;
        }
    }
    
    /**
     * A method to get model of the knight view
     * @return grid piece
     */
    @Override
    public KnightData getModel() {
        return model;
    }
    
    /**
     * A method to get screen bounds of the knight view
     * @return screen bounds
     */
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    /**
     * Draws the knight view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        boolean setBounds = true;
        boolean drawCross = false;
        if (model.getPosition() == null) {
            Rectangle box = GamePaletteView.getPaletteBox(index);
            int centerX = (int) (box.x + box.width /
                    2 - GameConstants.EDITOR_GRID_B / 2);
            int centerY = (int) (box.y + box.height /
                    2 - GameConstants.EDITOR_GRID_B / 2);
            GamePaletteView.drawPaletteFrame(graphics, box, isSelected);
            bounds.setBound(box);
            setBounds = false;
            if (isSelected) {
                DrawUtils.setAttributes(graphics,
                        Color.BLACK, getColor(), 4);
                setParameters(dragX - gridB /
                        2, dragY - gridB / 2, gridB);
                drawCross = !previewSuitable;
            } else {
                DrawUtils.setAttributes(graphics,
                        Color.BLACK, getColor(), 4);
                setParameters(centerX, centerY, GameConstants.EDITOR_GRID_B);
            }
        } else {
            DrawUtils.setAttributes(graphics,
                    Color.BLACK, getColor(), 4);
            setParameters(gridX, gridY, gridB);
        }
        
        drawKnight(graphics, setBounds, drawCross);
    }

    /**
     * A method to draw knight view
     * @param graphics Graphics object
     * @param drawCross draws a cross if given as true
     * @param setBounds if true set bounds
     */
    private void drawKnight(GraphicsContext graphics, boolean setBounds, boolean drawCross) {
        Coordinate currentPosition = (model.getPosition() != null) ?
                model.getPosition() : Coordinate.ZERO;
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

    /**
     * A method to get color of the knight
     * @return knight color
     */
    private Color getColor() {
            return model.isEnemy ? Game.getInstance().settingsManager.getEnemyColor():
                    Game.getInstance().settingsManager.getAllyColor();
    }
    
}
