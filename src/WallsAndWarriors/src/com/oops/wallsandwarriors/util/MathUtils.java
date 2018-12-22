package com.oops.wallsandwarriors.util;

public class MathUtils {

    /**
     * Method that checks the equality of two points
     * @param p1 point 1
     * @param p2 point 2
     * @return true if they are same
     */
    public static boolean equals(Point p1, Point p2) {
        final double EPSILON = 0.0001;
        return  Math.abs(p1.x - p2.x) < EPSILON &&
                Math.abs(p1.y - p2.y) < EPSILON;
    }
    
}
