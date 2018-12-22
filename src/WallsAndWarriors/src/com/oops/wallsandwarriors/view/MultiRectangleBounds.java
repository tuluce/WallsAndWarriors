package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents multiple rectanges as ScreenBounds
 * @author Emin Bahadir Tuluce
 */
public class MultiRectangleBounds implements ScreenBounds {

    private final List<Rectangle> rectangleList;
    
    /**
     * Creates a new MultiRectangleBounds
     */
    public MultiRectangleBounds() {
        rectangleList = new ArrayList<Rectangle>();
    }
    
    /**
     * Removes the rectangles associated with this region
     */
    public void clearBounds() {
        rectangleList.clear();
    }
    
    /**
     * Adds the rectangle to this region
     * @param bound the rectangular bound to be added
     */
    public void addBound(Rectangle bound) {
        rectangleList.add(bound);
    }
    
    /**
     * Checks if the given screen coordinate is in the region
     * @param x x component of the coordinate
     * @param y y component of the coordinate
     * @return true if the coordinate is inside
     */
    @Override
    public boolean contains(double x, double y) {
        for (Rectangle rectangle : rectangleList) {
            if (rectangle.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

}
