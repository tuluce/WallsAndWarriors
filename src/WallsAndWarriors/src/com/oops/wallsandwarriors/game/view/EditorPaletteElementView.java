package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.game.model.GridPiece;
import javafx.scene.canvas.GraphicsContext;

public class EditorPaletteElementView implements BoundedViewObject {
    
    private final GridPieceView innerView;
    
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
