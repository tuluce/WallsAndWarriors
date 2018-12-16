package com.oops.wallsandwarriors.view;

import com.oops.wallsandwarriors.util.Rectangle;

public class RectangleBounds implements ScreenBounds {

    private Rectangle bound;
    
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
    
    @Override
    public boolean contains(double x, double y) {
        return bound != null && bound.contains(x, y);
    }

}
