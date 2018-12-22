package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.model.Coordinate;
import java.awt.geom.Point2D;

/**
 * A class that represents a 2D point
 * @author Emin Bahadir Tuluce
 */
public class Point extends Point2D.Double {

    /**
     * Constructor to initialize a point
     * @param x x parameter of the point
     * @param y y parameter of the point
     */
    public Point(double x, double y) {
        super(x, y);
    }

    /**
     * Constructor to initialize a point with a coordinate
     * @param coordinate the coordinate of the point
     */
    public Point(Coordinate coordinate) {
        super(coordinate.x, coordinate.y);
    }
    
}
