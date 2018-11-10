package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.game.model.Coordinate;
import java.awt.geom.Point2D;

public class Point extends Point2D.Double {
    
    public Point(double x, double y) {
        super(x, y);
    }
    
    public Point(Coordinate coordinate) {
        super(coordinate.x, coordinate.y);
    }
    
}
