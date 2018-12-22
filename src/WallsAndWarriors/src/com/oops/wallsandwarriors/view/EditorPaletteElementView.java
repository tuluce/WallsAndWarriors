package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.model.GridPiece;
import javafx.scene.canvas.GraphicsContext;

/**
 * A class to implement palette element view of the challenge editor
 * @author Emin Bahadir Tuluce
 */
public class EditorPaletteElementView implements BoundedViewObject {
    
    private final GridPieceView innerView;

    /**
     * A constructor of the EditorPaletteElementView class
     * @param index indicates index
     * @param innerView grid piece view
     */
    public EditorPaletteElementView(int index, GridPieceView innerView) {
        this.innerView = innerView;
        this.innerView.setIndex(index);
    }
    
    /**
     * A method to get screen bounds of the clickable region
     * @return screen bounds
     */
    @Override
    public ScreenBounds getBounds() {
        return innerView.getBounds();
    }
    
    /**
     * Generates a new model according to the view
     * @return the generated GridPiece
     */
    public GridPiece generateModel() {
        return innerView.getModel().createCopy();
    }
    
    /**
     * A method to update drawing parameters
     * @param isSelected indicate to is selected or not
     * @param previewSuitable indicate to preview is suitable or not
     * @param dragX x value
     * @param dragY y value
     */
    public void update(boolean isSelected, boolean previewSuitable, double dragX, double dragY) {
        innerView.update(isSelected, previewSuitable, dragX, dragY);
    }
    
    /**
     * Draws the editor palette element view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        innerView.draw(graphics, deltaTime);
    }

}
