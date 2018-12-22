package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.model.GridPiece;

/**
 * A class to implement grid piece view
 * @author Emin Bahadir Tuluce
 */
public abstract class GridPieceView implements BoundedViewObject {

    // Positioning Properties
    protected int index;
    protected double gridX;
    protected double gridY;
    protected double gridB;
    
    // Model State
    protected boolean isSelected;
    protected boolean previewSuitable;
    protected double dragX;
    protected double dragY;
    
    // Drawing Parameters
    protected double screenX;
    protected double screenY;
    protected double blockLength;

    /**
     * A method to set parameters
     * @param screenX x coordinate of the screen
     * @param screenY y coordinate of the screen
     * @param blockLength length of the block
     */
    protected void setParameters(double screenX, double screenY, double blockLength) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.blockLength = blockLength;
    }

    /**
     * A method to set index value
     * @param index  index value
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * A method to update parameters
     * @param isSelected indicate to is selected or not
     * @param previewSuitable indicate to preview is suitable or not
     * @param dragX x value
     * @param dragY y value
     */
    public void update(boolean isSelected,
                       boolean previewSuitable, double dragX, double dragY) {
        this.isSelected = isSelected;
        this.previewSuitable = previewSuitable;
        this.dragX = dragX;
        this.dragY = dragY;
    }

    /**
     * A method to get model of the grid piece
     * @return grid piece
     */
    public abstract GridPiece getModel();
    
}
