package com.oops.wallsandwarriors.util;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * A class for debugging functionalities
 * @author Emin Bahadir Tuluce
 */
public class DebugUtils {
    
    public static final boolean DEBUGGING = false;

    /**
     * Method to print coordinates of X and Y in order to debug
     * @param scene is the scene of the game
     */
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
