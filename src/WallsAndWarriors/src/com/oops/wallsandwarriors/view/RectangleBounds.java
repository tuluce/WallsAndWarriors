package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.util.Rectangle;

/**
 * Represents a rectange shaped ScreenBounds
 * @author Emin Bahadir Tuluce
 */
public class RectangleBounds implements ScreenBounds {

    private Rectangle bound;
    
    /**
     * Changes the bounds to the given obe
     * @param bound the new rectangle bound
     */
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
    
    /**
     * Checks if the given screen coordinate is in the rectangular region
     * @param x x component of the coordinate
     * @param y y component of the coordinate
     * @return true if the coordinate is inside
     */
    @Override
    public boolean contains(double x, double y) {
        return bound != null && bound.contains(x, y);
    }

}
