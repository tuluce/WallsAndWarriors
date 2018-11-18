package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class MultiRectangleBounds implements ScreenBounds {

    private final List<Rectangle> rectangleList;
    
    public MultiRectangleBounds() {
        rectangleList = new ArrayList<Rectangle>();
    }
    
    public void clearBounds() {
        rectangleList.clear();
    }
    
    public void addBound(Rectangle bound) {
        rectangleList.add(bound);
    }
    
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
