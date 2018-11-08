package com.oops.wallsandwarriors.util;

import java.awt.geom.Point2D;

public class MathUtils {
    
    public static boolean equals(Point2D.Double p1, Point2D.Double p2) {
        final double EPSILON = 0.0001;
        return  Math.abs(p1.x - p2.x) < EPSILON &&
                Math.abs(p1.y - p2.y) < EPSILON;
    }
    
}
