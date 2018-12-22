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
    
    @Override
    public ScreenBounds getBounds() {
        return innerView.getBounds();
    }
    
    public GridPiece generateModel() {
        return innerView.getModel().createCopy();
    }
    
    public void update(boolean isSelected, boolean previewSuitable, double dragX, double dragY) {
        innerView.update(isSelected, previewSuitable, dragX, dragY);
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        innerView.draw(graphics, deltaTime);
    }

}
