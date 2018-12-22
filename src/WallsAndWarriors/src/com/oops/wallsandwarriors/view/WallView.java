package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.ChallengeManager;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.WallBastion;
import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.model.WallPortion;
import com.oops.wallsandwarriors.util.DrawUtils;
import com.oops.wallsandwarriors.util.MathUtils;
import com.oops.wallsandwarriors.util.Point;
import com.oops.wallsandwarriors.util.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Visual representation of wall object
 * @author Emin Bahadir Tuluce
 */
public class WallView extends GridPieceView {
    
    enum TipType {
        END,    // The tip is ending
        FIRST,  // The tip has a curve on the first quarter
        SECOND, // The tip has a curve on the second quarter
        THIRD,  // The tip has a curve on the third quarter
        FORTH   // The tip has a curve on the forth quarter
    }
    
    private final WallData model;
    private final MultiRectangleBounds bounds;
    
    /**
     * Creates a new WallView.
     * @param model the model for the wall view
     * @param gridX the grid x position 
     * @param gridY the grid y position
     * @param gridB the length of a grid block
     */
    public WallView(WallData model, double gridX, double gridY, double gridB) {
        this.model = model;
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridB = gridB;
        bounds = new MultiRectangleBounds();
        ChallengeManager challengeManager = Game.getInstance().challengeManager;
        index = challengeManager.getChallengeData().walls.indexOf(model);
    }
    
    /**
     * Creates a new WallView.
     * @param model the model for the wall view
     */
    public WallView(WallData model) {
        this(model, GameConstants.GRID_X, GameConstants.GRID_Y, GameConstants.GRID_B);
    }
    
    /**
     * Creates a new WallView.
     * @param model the model for the wall view
     * @param inEditor should be given true if the wall is in editor
     */
    public WallView(WallData model, boolean inEditor) {
        this(model);
        if (inEditor) {
            this.gridX = GameConstants.EDITOR_GRID_X;
            this.gridY = GameConstants.EDITOR_GRID_Y;
            this.gridB = GameConstants.EDITOR_GRID_B;
        }
    }
    
    /**
     * A method to get model of the wall view
     * @return grid piece
     */
    @Override
    public WallData getModel() {
        return model;
    }
    
    /**
     * A method to get screen bounds of the wall view
     * @return screen bounds
     */
    @Override
    public ScreenBounds getBounds() {
        return bounds;
    }
    
    /**
     * Draws the wall view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        bounds.clearBounds();
        if (model.getPosition() == null) {
            Rectangle box = GamePaletteView.getPaletteBox(index);
            int centerX = (int) (box.x + box.width / 2 - GameConstants.PALETTE_B / 2);
            int centerY = (int) (box.y + box.height / 2 - GameConstants.PALETTE_B / 2);
            GamePaletteView.drawPaletteFrame(graphics, box, isSelected);
            bounds.addBound(box);
            if (isSelected || index == -1) {
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
        for (WallPortion portion : model.getWallDefinition().portions) {
            Coordinate block1 = position.plus(portion.firstRelativePos);
            Coordinate block2 = position.plus(portion.secondRelativePos);
            TipType[] tips = getTipTypes(portion);
            drawWallTip(graphics, block1, block2, tips[0], tips[1]);
        }
        for (WallBastion bastion : model.getWallDefinition().bastions) {
            Coordinate block = position.plus(bastion.relativePos);
            drawWallBastion(graphics, block, model.getPosition() != null);
        }
    }
    
    private void drawWallTip(GraphicsContext graphics, Coordinate block1, Coordinate block2, TipType tip1, TipType tip2) {
        if (blockLength > 45) {
            Coordinate b1 = getSmaller(block1, block2);
            Coordinate b2 = getBigger(block1, block2);

            double a = screenX + (b1.x + 0.5) * blockLength;
            double b = screenY + (b1.y + 0.5) * blockLength;
            double c = screenX + (b2.x + 0.5) * blockLength;
            double d = screenY + (b2.y + 0.5) * blockLength;
            double l = blockLength;

            Point difference = new Point(Math.abs(a - c), Math.abs(b - d));
            final Point HORIZONTAL = new Point(l, 0);
            final Point VERTICAL = new Point(0, l);
            double thickness = blockLength * GameConstants.WALL_THICKNESS;
            double t = thickness / 2;
            double tt = thickness;
            double ttt = thickness * 1.5;
            graphics.setFill(Color.WHITE);
            if (MathUtils.equals(difference, HORIZONTAL)) {
                if (tip1 == TipType.END) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - t, b - l / 2, tt, tt, 0, 180);
                } else if (tip1 == TipType.FIRST) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - tt, b - l / 2 - t, ttt, ttt, 0, 90);
                } else if (tip1 == TipType.SECOND) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - t, b - l / 2 - t, ttt, ttt, 90, 90);
                }

                if (tip2 == TipType.END) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - t, b + l / 2 - tt, tt, tt, 180, 180);
                } if (tip2 == TipType.THIRD) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - t, b + l / 2 - tt, ttt, ttt, 180, 90);
                } else if (tip2 == TipType.FORTH) {
                    DrawUtils.drawArc(graphics, (a + c) / 2 - tt, b + l / 2 - tt, ttt, ttt, 270, 90);
                }
            } else if (MathUtils.equals(difference, VERTICAL)) {
                if (tip1 == TipType.END) {
                    DrawUtils.drawArc(graphics, a - l / 2, (b + d) / 2 - t, tt, tt, 90, 180);
                }
                if (tip2 == TipType.END) {
                    DrawUtils.drawArc(graphics, a + l / 2 - tt, (b + d) / 2 - t, tt, tt, 270, 180);
                }
            } else {
                throw new RuntimeException("Unexpected wall difference: " + difference);
            }
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
                double t = thickness / 2;
                
                graphics.fillRect(x, y + t / 2 - 1, w, h - t + 2);
                graphics.strokeLine((a + c) / 2 + t, b - l / 2 + t,
                                    (a + c) / 2 + t, b + l / 2 - t);
                graphics.strokeLine((a + c) / 2 - t, b - l / 2 + t,
                                    (a + c) / 2 - t, b + l / 2 - t);
                
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
                double t = thickness / 2;
                
                graphics.fillRect(x + t / 2 - 1, y, w - t + 2, h);
                graphics.strokeLine(a - l / 2 + t, (b + d) / 2 + t,
                                    a + l / 2 - t, (b + d) / 2 + t);
                graphics.strokeLine(a - l / 2 + t, (b + d) / 2 - t,
                                    a + l / 2 - t, (b + d) / 2 - t);
                
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

    
    private TipType[] getTipTypes(WallPortion portion) {
        
        TipType[] tips = { TipType.END, TipType.END };
        
        Coordinate block1 = getSmaller(portion.firstRelativePos, portion.secondRelativePos);
        Coordinate block2 = getBigger(portion.firstRelativePos, portion.secondRelativePos);
        
        for (WallPortion otherPortion : model.getWallDefinition().portions) {
            Coordinate other1 = getSmaller(otherPortion.firstRelativePos, otherPortion.secondRelativePos);
            Coordinate other2 = getBigger(otherPortion.firstRelativePos, otherPortion.secondRelativePos);
            
            // If the wall is horizontal and the other is vertical,
            if (block1.x == block2.x && other1.y == other2.y) {
                
                if (block1.equals(other1)) {
                    tips[1] = TipType.FORTH;
                }
                else if (block1.equals(other2)) {
                    tips[0] = TipType.THIRD;
                }
                else if (block2.equals(other1)) {
                    tips[1] = TipType.FIRST;
                }
                else if (block2.equals(other2)) {
                    tips[0] = TipType.SECOND;
                }
                
            }
            
            // If the wall is vertical and the other is horizontal,
            else if (block1.y == block2.y && other1.x == other2.x) {
                
                if (block1.equals(other1)) {
                    tips[1] = TipType.FORTH;
                }
                else if (block1.equals(other2)) {
                    tips[0] = TipType.FIRST;
                }
                else if (block2.equals(other1)) {
                    tips[1] = TipType.THIRD;
                }
                else if (block2.equals(other2)) {
                    tips[0] = TipType.SECOND;
                }
                
            }
        }
        
        return tips;
    }
    
    private Coordinate getSmaller(Coordinate block1, Coordinate block2) {
        if (block1.x == block2.x) {
            if (block1.y < block2.y) {
                return block1;
            } else {
                return block2;
            }
        } else {
            if (block1.x < block2.x) {
                return block1;
            } else {
                return block2;
            }
        }
    }
    
    private Coordinate getBigger(Coordinate block1, Coordinate block2) {
        if (block1.x == block2.x) {
            if (block1.y > block2.y) {
                return block1;
            } else {
                return block2;
            }
        } else {
            if (block1.x > block2.x) {
                return block1;
            } else {
                return block2;
            }
        }
    }
     
}
