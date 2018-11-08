package com.oops.wallsandwarriors.game.view;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class WallBounds implements ScreenBounds {

    public final List<Rectangle2D.Double> rectangleList;
    
    public WallBounds() {
        rectangleList = new ArrayList<Rectangle2D.Double>();
    }
    
    public void clearBounds() {
        rectangleList.clear();
    }
    
    public void addBound(Rectangle2D.Double bound) {
        rectangleList.add(bound);
    }
    
    @Override
    public boolean contains(double x, double y) {
        for (Rectangle2D.Double rectangle : rectangleList) {
            if (rectangle.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

}
