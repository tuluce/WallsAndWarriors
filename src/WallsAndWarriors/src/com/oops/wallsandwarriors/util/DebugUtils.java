package com.oops.wallsandwarriors.util;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class DebugUtils {
    
    public static final boolean DEBUGGING = true;

    public static void initClickDebugger(Scene scene) {
        scene.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (DEBUGGING) {
                        System.out.println("(" + (int) e.getX() + ", " + (int) e.getY() + ")");
                    }
                }
            }
        );
    }
    
}
