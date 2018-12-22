package com.oops.wallsandwarriors.view;

import javafx.scene.canvas.GraphicsContext;

/**
 * Visual representation of a general view object
 * @author Emin Bahadir Tuluce
 */
public interface ViewObject {
    
    /**
     * Draws the view object on the screen
     * @param graphics the graphics object for rendering
     * @param deltaTime the time difference until last render
     */
    public void draw(GraphicsContext graphics, double deltaTime);
    
}
