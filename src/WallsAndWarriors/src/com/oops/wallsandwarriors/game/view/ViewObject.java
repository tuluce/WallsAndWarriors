package com.oops.wallsandwarriors.game.view;

import javafx.scene.canvas.GraphicsContext;

public interface ViewObject {
    
    public void draw(GraphicsContext graphics, double deltaTime);
    
}
