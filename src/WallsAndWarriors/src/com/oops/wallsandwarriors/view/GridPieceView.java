package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.model.GridPiece;

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
    
    protected void setParameters(double screenX, double screenY, double blockLength) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.blockLength = blockLength;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void update(boolean isSelected, boolean previewSuitable, double dragX, double dragY) {
        this.isSelected = isSelected;
        this.previewSuitable = previewSuitable;
        this.dragX = dragX;
        this.dragY = dragY;
    }
    
    public abstract GridPiece getModel();
    
}
