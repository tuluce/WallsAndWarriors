package com.oops.wallsandwarriors.view;

/**
 * Represents a region on the screen
 * @author Emin Bahadir Tuluce
 */
public interface ScreenBounds {
    
    /**
     * Checks if the given screen coordinate is in the region
     * @param x x component of the coordinate
     * @param y y component of the coordinate
     * @return true if the coordinate is inside
     */
    public boolean contains(double x, double y);
    
}
