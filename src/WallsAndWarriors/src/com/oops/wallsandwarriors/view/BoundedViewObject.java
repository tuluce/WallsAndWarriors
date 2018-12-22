package com.oops.wallsandwarriors.view;

/**
 * An interface of the bounded view object
 * @author Emin Bahadir Tuluce
 */
public interface BoundedViewObject extends ViewObject {

    /**
     * A method to get screen bounds of the view object
     * @return screen bounds
     */
    public ScreenBounds getBounds();
    
}
