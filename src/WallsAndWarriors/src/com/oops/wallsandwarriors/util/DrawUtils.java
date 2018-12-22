package com.oops.wallsandwarriors.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

/**
 * A class to draw figures
 * @author OOPs
 */
public class DrawUtils {

    /**
     * Method to set attributes to an object
     * @param graphics is the graphics instantiation
     * @param stroke is the stroke that will be set to an object
     * @param fill is the fill that will be set to an object
     * @param lineWidth is the line width that will be set to an object
     */
    public static void setAttributes(GraphicsContext graphics, Paint stroke, Paint fill, double lineWidth) {
        graphics.setFill(fill);
        graphics.setStroke(stroke);
        graphics.setLineWidth(lineWidth);
    }



    /**
     * Method to draw a rectangle
     * @param graphics is the graphics instantiation
     * @param x is the x parameter of the rectangle
     * @param y is the y parameter of the rectangle
     * @param width is the width of the rectangle
     * @param height is the height of the rectangle
     */
    public static void drawRect(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.fillRect(x, y, width, height);
        graphics.strokeRect(x, y, width, height);
    }




    /**
     * Method to draw a round rectangle
     * @param graphics is the graphics instantiation
     * @param x is the x parameter of the round rectangle
     * @param y is the y parameter of the round rectangle
     * @param width is the width of the round rectangle
     * @param height is the height of the round rectangle
     * @param arc is the arc of the round rectangle
     */
    public static void drawRoundRect(GraphicsContext graphics, double x, double y, double width, double height, double arc) {
        graphics.fillRoundRect(x, y, width, height, arc, arc);
        graphics.strokeRoundRect(x, y, width, height, arc, arc);
    }




    /**
     * Method to draw an oval
     * @param graphics is the graphics instantiation
     * @param x is the x parameter of the oval
     * @param y is the y parameter of the oval
     * @param width is the width of the oval
     * @param height is the height of the oval
     */
    public static void drawOval(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.fillOval(x, y, width, height);
        graphics.strokeOval(x, y, width, height);
    }




    /**
     * Method to draw an arc
     * @param graphics is the graphics instantiation
     * @param x is the x parameter of the arc
     * @param y is the y parameter of the arc
     * @param width is the width of the arc
     * @param height is the height of the arc
     * @param startAngle is the angle that presents start angle
     * @param arcExtent, arc extend
     */
    public static void drawArc(GraphicsContext graphics, double x, double y, double width, double height, double startAngle, double arcExtent) {
        graphics.fillArc(x, y, width, height, startAngle, arcExtent, ArcType.ROUND);
        graphics.strokeArc(x, y, width, height, startAngle, arcExtent, ArcType.OPEN);
    }
    
}
