package com.oops.wallsandwarriors.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DrawUtils {
    
    public static void setAttributes(GraphicsContext graphics, Paint stroke, Paint fill, double lineWidth) {
        graphics.setFill(fill);
        graphics.setStroke(stroke);
        graphics.setLineWidth(lineWidth);
    }
    
    public static void drawRect(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.fillRect(x, y, width, height);
        graphics.strokeRect(x, y, width, height);
    }
    
    public static void drawRoundRect(GraphicsContext graphics, double x, double y, double width, double height, double arc) {
        graphics.fillRoundRect(x, y, width, height, arc, arc);
        graphics.strokeRoundRect(x, y, width, height, arc, arc);
    }
    
    public static void drawOval(GraphicsContext graphics, double x, double y, double width, double height) {
        graphics.fillOval(x, y, width, height);
        graphics.strokeOval(x, y, width, height);
    }
    
}
