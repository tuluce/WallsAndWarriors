package com.oops.wallsandwarriors.util;

import java.awt.geom.Rectangle2D;

/**
 * A class that distributes a rectangle
 * @author OOPs
 */
public class Rectangle extends Rectangle2D.Double {

    /**
     * Constructor to initialize a rectangle
     * @param x x parameter of the rectangle
     * @param y y parameter of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
}
