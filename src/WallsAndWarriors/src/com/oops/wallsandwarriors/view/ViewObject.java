package com.oops.wallsandwarriors.view;

import javafx.scene.canvas.GraphicsContext;

public interface ViewObject {
    
    public void draw(GraphicsContext graphics, double deltaTime);
    
}
